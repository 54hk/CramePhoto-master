package com.example.a54hk.cramephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IFootDialogCallBack {

    Button addImg;
    CramePhoto mCameraPhoto;
    ImageView playImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initClick();
        mCameraPhoto = new CramePhoto(this);

    }

    private void initClick() {
        addImg = (Button) findViewById(R.id.add_img);
        addImg.setOnClickListener(this);
        playImg = (ImageView) findViewById(R.id.play_img);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_img:
                PhotoUtils.showDialog(this, this);
                break;
        }
    }

    @Override
    public void dialogCallback(int id) {

            switch (id) {
                case R.id.bt_cramre:
                    mCameraPhoto.openPhotoGallery();
                    break;
                case R.id.bt_photo:
                    mCameraPhoto.openCamera();
                    break;
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case AppConfig.PHOTO_REQUEST_TAKE_PHOTO:
                AppConfig.mUri = FileProvider.getUriForFile(MainActivity.this,"com.example.a54hk.cramephoto.fileprovider"
                        ,mCameraPhoto.tempFile);
                showImage(FileProvider.getUriForFile(MainActivity.this,"com.example.a54hk.cramephoto.fileprovider"
                        ,mCameraPhoto.tempFile));
                break;
            case AppConfig.PHOTO_REQUEST_GALLERY:
                if (data == null) break;
                AppConfig.mUri = data.getData();
                showImage(data.getData());
                break;
            case AppConfig.PHOTO_REQUEST_CUT:// 返回的结果
                Bitmap photo = null;
                if (data != null) {
                    if (data.getExtras() == null) {
                        Uri uri = data.getData();
                        try {
                            photo = MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        photo = data.getExtras().getParcelable("data");
                    }
//                    Bitmap photo = data.getExtras().getParcelable("data");
                    if (photo != null) {
//                        PhotoUtils.saveBitmap(photo, "user_tmp_head.png");  对图片进行保存
//                        uploadUserHead(ivHeaderImage, photo);    进行网络请求 并 setImag给Imagview
//                        FileUtils.deleteFile(mCameraPhoto.tempFile);
                        playImg.setImageBitmap(photo);
                    }
                }
                break;
        }
    }

//   裁剪你想要的图片
     private void showImage(Uri uri) {
        mCameraPhoto.startPhotoZoom(uri);
    }


}
