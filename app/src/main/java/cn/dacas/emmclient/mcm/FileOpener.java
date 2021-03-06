package cn.dacas.emmclient.mcm;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import cn.dacas.emmclient.R;

public class FileOpener {
	private Context context;
	public FileOpener(Context context){
		this.context = context;
	}
	
    //android获取一个用于打开HTML文件的intent
    public static Intent getHtmlFileIntent(File file)
    {
        Uri uri = Uri.parse(file.toString()).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(file.toString()).build();
        Intent intent = new Intent("android.intent.action.DCSVIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }
  //android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(File file)
    {
    	Intent intent = new Intent(Intent.ACTION_VIEW); 
    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	intent.setDataAndType(Uri.fromFile(file), "image/*");
        return intent;
    }
    //android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(File file)
    {
      Intent intent = new Intent("android.intent.action.DCSVIEW");
      intent.addCategory("android.intent.category.DEFAULT");
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      Uri uri = Uri.fromFile(file);
      intent.setDataAndType(uri, "application/pdf");
      return intent;
    }
  //android获取一个用于打开文本文件的intent
  public static Intent getTextFileIntent(File file)
  {    
    Intent intent = new Intent("android.intent.action.DCSVIEW");
    intent.addCategory("android.intent.category.DEFAULT");
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Uri uri = Uri.fromFile(file);
    intent.setDataAndType(uri, "text/plain");
    return intent;
  }
 
  //android获取一个用于打开音频文件的intent
    public static Intent getAudioFileIntent(File file)
    {
      Intent intent = new Intent("android.intent.action.DCSVIEW");
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      intent.putExtra("oneshot", 0);
      intent.putExtra("configchange", 0);
      Uri uri = Uri.fromFile(file);
      intent.setDataAndType(uri, "audio/*");
      return intent;
    }
    //android获取一个用于打开视频文件的intent
    public static Intent getVideoFileIntent(File file)
    {
      Intent intent = new Intent("android.intent.action.DCSVIEW");
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      intent.putExtra("oneshot", 0);
      intent.putExtra("configchange", 0);
      Uri uri = Uri.fromFile(file);
      intent.setDataAndType(uri, "video/*");
      return intent;
    }
 
 
    //android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(File file)
    {
      Intent intent = new Intent("android.intent.action.DCSVIEW");
      intent.addCategory("android.intent.category.DEFAULT");
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      Uri uri = Uri.fromFile(file);
      intent.setDataAndType(uri, "application/x-chm");
      return intent;
    }
 
 
  //android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(File file)
    {
      Intent intent = new Intent("android.intent.action.DCSVIEW");
      intent.addCategory("android.intent.category.DEFAULT");
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      Uri uri = Uri.fromFile(file);
      intent.setDataAndType(uri, "application/msword");
      return intent;
    }
  //android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(File file)
    {
      Intent intent = new Intent("android.intent.action.DCSVIEW");
      intent.addCategory("android.intent.category.DEFAULT");
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      Uri uri = Uri.fromFile(file);
      intent.setDataAndType(uri, "application/vnd.ms-excel");
      return intent;
    }
  //android获取一个用于打开PPT文件的intent
    public static Intent getPPTFileIntent(File file)
    {
      Intent intent = new Intent("android.intent.action.DCSVIEW");
      intent.addCategory("android.intent.category.DEFAULT");
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      Uri uri = Uri.fromFile(file);
      intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
      return intent;
    }
    //android获取一个用于打开apk文件的intent
    public static Intent getApkFileIntent(File file)
    {
        Intent intent = new Intent();  
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        intent.setAction(android.content.Intent.ACTION_VIEW);  
        intent.setDataAndType(Uri.fromFile(file),  "application/vnd.android.package-archive");  
        return intent;
    }
	
	public boolean checkEndsWithInStringArray(String checkItsEnd, 
            String[] fileEndings){
		for(String aEnd : fileEndings){
			if(checkItsEnd.endsWith(aEnd))
				return true;
		}
		return false;
	}
	
	public void openFile(File currentPath) {
		if(currentPath!=null&&currentPath.isFile())
        {
            String fileName = currentPath.toString();
            Intent intent;
            if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingImage))){
                intent = getImageFileIntent(currentPath);
                context.startActivity(intent);
            }else if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingWebText))){
                intent = getHtmlFileIntent(currentPath);
                context.startActivity(intent);
            }else if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingPackage))){
                intent = getApkFileIntent(currentPath);
                context.startActivity(intent);

            }else if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingAudio))){
                intent = getAudioFileIntent(currentPath);
                context.startActivity(intent);
            }else if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingVideo))){
                intent = getVideoFileIntent(currentPath);
                context.startActivity(intent);
            }else if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingText))){
                intent = getTextFileIntent(currentPath);
                context.startActivity(intent);
            }else if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingPdf))){
                intent = getPdfFileIntent(currentPath);
                context.startActivity(intent);
            }else if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingWord))){
                intent = getWordFileIntent(currentPath);
                context.startActivity(intent);
            }else if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingExcel))){
                intent = getExcelFileIntent(currentPath);
                context.startActivity(intent);
            }else if(checkEndsWithInStringArray(fileName, context.getResources().
                    getStringArray(R.array.fileEndingPPT))){
                intent = getPPTFileIntent(currentPath);
                context.startActivity(intent);
            }else{
            	Toast.makeText(context.getApplicationContext(), "无法打开文件"+currentPath.getName(), Toast.LENGTH_SHORT).show();
            }
        }else{
        	Toast.makeText(context.getApplicationContext(), "没有需要打开的文件", Toast.LENGTH_SHORT).show();
        }
	}
}
