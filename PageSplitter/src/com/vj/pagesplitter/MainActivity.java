package com.vj.pagesplitter;


import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;


public class MainActivity extends Activity {

	String htmlStrng ="<!DOCTYPE html><html><body><p>At the core of Android Studio is an intelligent code editor capable of advanced code completion, refactoring, and code analysis.The powerful code editor helps you be a more productive Android app developer..</p><p>This is a paragraph.</p><p>TAt the core of Android Studio is an intelligent code editor capable of advanced code completion, refactoring, and code analysis.The powerful code editor helps you be a more productive Android app developer.</p></body></html>";
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DisplayMetrics dimension = new DisplayMetrics();
		getWindowManager().getDefaultDisplay()
				.getMetrics(dimension);
		int w = dimension.widthPixels;
		int h = dimension.heightPixels;

	//	PageSplitter_Articles pageSplitter = new PageSplitter_Articles(w-80, h-80
			//	, 1.3f, 1);

		NativePageSplitter pageSplitter= new NativePageSplitter(w-70, h-70
				, 1.2f, 1);
		TextPaint textPaint = new TextPaint();
		textPaint.setTextSize(15);
	
		pageSplitter.append(Html.fromHtml(htmlStrng));
		pageSplitter.split(textPaint);

	
		String[] arr = new String[pageSplitter.getPages().size()];
		for (int i = 0; i < pageSplitter.getPages().size(); i++) {
			arr[i] = pageSplitter.getPages().get(i).toString();
			
			Log.e("SM", "arr[i] " +arr[i]);
		}
    }
}
