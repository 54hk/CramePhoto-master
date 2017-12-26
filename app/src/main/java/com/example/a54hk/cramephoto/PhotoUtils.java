package com.example.a54hk.cramephoto;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;




import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class PhotoUtils {

    public static int margin = 1;//二维码白边距离，1~4


    /**
     * 头像截取dialog
     * 使用方法：在需要调用接口的回调中实现dialog
     *
     * @param activity
     * @param mClickListener
     */

    public static void showDialog(Activity activity, final IFootDialogCallBack mClickListener) {
        View view = View.inflate(activity, R.layout.photo_choose_dialog, null);
        final Dialog dialog = new Dialog(activity, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.style_dialog_show_foot);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = activity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        View cameraBtn = view.findViewById(R.id.bt_cramre);
        View photoBtn = view.findViewById(R.id.bt_photo);
        View cancelBtn = view.findViewById(R.id.bt_cancle);

        photoBtn.setBackgroundResource(R.drawable.photo_camera_selector);
        cameraBtn.setBackgroundResource(R.drawable.photo_gallery_selector);
        cameraBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.dialogCallback(R.id.bt_cramre);
                        dialog.cancel();
                    }
                });
        photoBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.dialogCallback(R.id.bt_photo);
                        dialog.cancel();
                    }
                });
        cancelBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

    }

    /**
     * 转换bigmap 为加密后的字符串的方法
     *
     * @param picture
     * @return
     */
    public static String jpgToPng(Bitmap picture) {
        String basePicture = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
        picture.compress(Bitmap.CompressFormat.PNG, 100, baos);
        try {
            baos.close();
            byte[] buffer = baos.toByteArray();
            // 将图片的字节流数据加密成base64字符输出
            basePicture = Base64.encodeToString(buffer, 0, buffer.length,
                    Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basePicture;
    }


    /**
     * 保存方法
     */
    public static void saveBitmap(Bitmap picture, String picName) {
//        Log.e("Utils", "保存图片");
//        File f = new File("/sdcard/namecard/", picName);
        File f = new File("", picName);  // 图片的路径 SD卡 或者是手机
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            picture.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static Bitmap create2DCode(String str) {
//        Bitmap bitmap = null;
//        BitMatrix result = null;
//
//        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
//        // 指定纠错等级
//        hints.put(EncodeHintType.ERROR_CORRECTION, level);
//        // 指定编码格式
//        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        hints.put(EncodeHintType.MARGIN, margin);   //设置白边
//
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        try {
//            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 800, 800, hints);
//            // 使用 ZXing Android Embedded 要写的代码
//            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//            bitmap = barcodeEncoder.createBitmap(result);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException iae) { // ?
//            return null;
//        }
//
//        return bitmap;
//    }
//
//    /**
//     * 在二维码中间添加Logo图案
//     */
//    private static Bitmap addLogo(Bitmap src) {
//        if (src == null) {
//            return null;
//        }
//
//        Bitmap logo = BitmapFactory.decodeResource(AppUtils.getContext().getResources(), R.mipmap.zbj_launcher);
//
//        //获取图片的宽高
//        int srcWidth = src.getWidth();
//        int srcHeight = src.getHeight();
//        int logoWidth = logo.getWidth();
//        int logoHeight = logo.getHeight();
//
//        if (srcWidth == 0 || srcHeight == 0) {
//            return null;
//        }
//
//        if (logoWidth == 0 || logoHeight == 0) {
//            return src;
//        }
//        //logo大小为二维码整体大小的1/5
//        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
//        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
//        try {
//            Canvas canvas = new Canvas(bitmap);
//            canvas.drawBitmap(src, 0, 0, null);
//            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
//            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
//
//            canvas.save(Canvas.ALL_SAVE_FLAG);
//            canvas.restore();
//        } catch (Exception e) {
//            bitmap = null;
//            e.getStackTrace();
//        }
//
//
//        return bitmap;
//    }

}
