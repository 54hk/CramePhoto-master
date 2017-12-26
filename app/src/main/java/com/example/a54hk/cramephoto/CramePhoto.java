package com.example.a54hk.cramephoto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * dialog 获取图片
 * Created by eric on 2017/9/11.
 *
 */
public class CramePhoto {

	public File tempFile=new File(Environment.getExternalStorageDirectory(),
			getPhotoFileName());

	Activity mActivity;
	public CramePhoto(Activity mActivity) {
		this.mActivity =mActivity;
	}
	
	/**
	 * 打开系统相册
	 * 
	 * @param
	 */
	public void openPhotoGallery() {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				"image/*");
		mActivity.startActivityForResult(intent,
				AppConfig.PHOTO_REQUEST_GALLERY);
	}

	/**
	 * 打开照相机
	 * 
	 * @param
	 */
	public void openCamera() {
		Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 指定调用相机拍照后照片的储存路径
		cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(mActivity
				,"com.example.a54hk.cramephoto.fileprovider",tempFile));
		mActivity.startActivityForResult(cameraintent,
				AppConfig.PHOTO_REQUEST_TAKE_PHOTO);
	}
	/**
	 * 打开剪裁界面
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");
//		intent.putExtra("output", uri);

		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", true);
		intent.putExtra("noFaceDetection", false);
//		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		mActivity.startActivityForResult(intent,
				AppConfig.PHOTO_REQUEST_CUT);
	}


	// 使用系统当前日期加以调整作为照片的名称
	@SuppressLint("SimpleDateFormat")
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".png";
	}

}
