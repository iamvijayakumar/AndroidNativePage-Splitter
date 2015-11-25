package com.vj.pagesplitter;
import android.text.DynamicLayout;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class NativePageSplitter {
    private final int pageWidth;
    private final int pageHeight;
    private final float lineSpacingMultiplier;
    private final float lineSpacingExtra;
    private final List<CharSequence> pages = new ArrayList<CharSequence>();
    private SpannableStringBuilder mSpannableStringBuilder = new SpannableStringBuilder();

    public NativePageSplitter(int pageWidth, int pageHeight, float lineSpacingMultiplier, float lineSpacingExtra) {
        this.pageWidth = pageWidth;
        this.pageHeight = pageHeight;
        this.lineSpacingMultiplier = lineSpacingMultiplier;
        this.lineSpacingExtra = lineSpacingExtra;
    }

    public void append(CharSequence charSequence) {
        mSpannableStringBuilder.append(charSequence);
    }

    public void split(TextPaint textPaint) {
        DynamicLayout staticLayout = new DynamicLayout(
                mSpannableStringBuilder,
                textPaint,
                pageWidth,
               null,
                lineSpacingMultiplier,
                lineSpacingExtra,
                true
        );
        int startLine = 0;
    //	Log.e("SM", "pages :: ####### &&&&  " +staticLayout.getLineCount());
        while(startLine < staticLayout.getLineCount()) {
        	// for(startLine =0; startLine< staticLayout.getLineCount(); startLine++) {
            int startLineTop = staticLayout.getLineTop(startLine);
            int endLine = staticLayout.getLineForVertical(startLineTop + pageHeight);
            int endLineBottom = staticLayout.getLineBottom(endLine);
            int lastFullyVisibleLine;
            if(endLineBottom > startLineTop + pageHeight){
                lastFullyVisibleLine = endLine - 1;
            }
            else{
                lastFullyVisibleLine = endLine;
                }
            int startOffset = staticLayout.getLineStart(startLine);
            Log.e("SM", "######## :lastFullyVisibleLine::::   " + lastFullyVisibleLine);
            int endOffset = staticLayout.getLineEnd(lastFullyVisibleLine);
            pages.add(mSpannableStringBuilder.subSequence(startOffset, endOffset));
            startLine = lastFullyVisibleLine + 1;
        }
    }

    public List<CharSequence> getPages() {
    	
    	//Log.e("SM", "pages :: ####### " +pages.size());
        return pages;
    }
}