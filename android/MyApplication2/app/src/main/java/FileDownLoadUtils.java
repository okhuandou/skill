import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.text.TextUtils;

import com.example.sdk_demo.ContantData;

public class FileDownLoadUtils {
	
	//设置两个字段
	public static final int  DOWNLOAD_FOR_BROWSER = 0;
	public static final int DOWNLOAD_FOR_ONESELF = 1;
	public static final int DOWNLOAD_BY_MANAGER = 2;
	private Context context;
	public FileDownLoadUtils(Context context) {
		
		super();
		this.context = context;
	
	}

	@SuppressLint("NewApi")
	public void downloadFile(final Context context, final String url, int method) {
		try {
			switch (method) {
			case 0:
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				context.startActivity(intent);	
				break;

			case 1:
//				String stringName = ToolsUtils.getCurrentTime() + ".apk";
				String stringName = url.substring(url.lastIndexOf("/") + 1, url.length());
				

				final String loadPath = ContantData.PATH + "/" + stringName;
				
				
					new Thread() {
						public void run() {
							try {
								if(!TextUtils.isEmpty(url)) {
									File file = new File(loadPath);
									if(!file.exists()) {
										//下载app
										byte[] byt = new byte[1024];
										FileOutputStream fos = new FileOutputStream(file);
//										BufferedWriter bw = new BufferedWriter(fw);
										URL urlValue = new URL(url);
										HttpURLConnection conn = (HttpURLConnection)urlValue.openConnection();
										conn.setRequestMethod("GET");
										conn.setReadTimeout(5 * 1000);
										conn.setConnectTimeout(5 * 1000);
										if(conn.getResponseCode() == 200) {
											InputStream inputStream = conn.getInputStream();
											int len = 0;
											while((len = inputStream.read(byt)) > 0) {
												fos.write(byt, 0, len);
												fos.flush();
											}
											fos.close();
											inputStream.close();
											
										}
									}

									
									doApk(context, loadPath, null);

									
									
								}
							} catch (Exception e) {
								System.out.println("错误" + e.getMessage());
							}
						}
					}.start();
				
//				doApk(context, loadPath);

				
				break;
			case 2:
				//创建一个下载存储位置
				String downloadStringName = url.substring(url.lastIndexOf("/") + 1, url.length());
				final String downloadPath = ContantData.PATH + "/" + downloadStringName;
				File file = new File(downloadPath);
				
				//一旦完成下载就调用广播执行代码
				IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
				//创建一个广播
				final BroadcastReceiver receiver = new BroadcastReceiver() {
					
					@Override
					public void onReceive(Context context, Intent intent) {
						//土司出不来是因为我们在弹出土司之前就已经把这样页面删除了
//						Toast.makeText(context, "通过downloadManager下载完成了", Toast.LENGTH_SHORT).show();
						System.out.println("哈哈哈哈下载啦");
						doApk(context, downloadPath, this);
					}
				};
				//注册广播
				context.registerReceiver(receiver, intentFilter);
				DownloadManager downloadManager = (DownloadManager)(context.getSystemService(Context.DOWNLOAD_SERVICE));
				Uri downloadUri = Uri.parse(url);
				Request request = new Request(downloadUri);
				request.setDestinationUri(Uri.fromFile(file));
				downloadManager.enqueue(request);
				break;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	protected void doApk(Context context, String path, BroadcastReceiver receiver) {
		
		try {
			File file = new File(path);
			if(file.exists()) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
				context.startActivity(intent);
				if(receiver != null) {
					context.unregisterReceiver(receiver);
				}
				//这里发送一个上报信息，需求以后补充
				((Activity) context).finish(); 
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
