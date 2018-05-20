package betwarendpoint.net.cardscaner;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class CameraSurfaceTestFragment extends Fragment {

    CameraPreview mCameraPreview;

    public CameraSurfaceTestFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_butter, container, false);
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        mCameraPreview = view.findViewById(R.id.camera_preview);
        setupCameraPreview();
    }


    private void setupCameraPreview() {
        mCameraPreview.setCameraReadyListener(new CameraPreview.CameraReadyListener() {

            @Override
            public void onCameraReady(Camera camera) {
                camera.setPreviewCallback(new Camera.PreviewCallback() {

                    @Override
                    public void onPreviewFrame(byte[] data, Camera camera) {
                        Camera.Parameters parameters = camera.getParameters();
                        Camera.Size size = parameters.getPreviewSize();

                        // Convert uncompressed data to a JPEG
                        YuvImage yuvimage = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        yuvimage.compressToJpeg(new Rect(0, 0, size.width, size.height), 80, baos);
                        byte[] jpegData = baos.toByteArray();

                        // Create a Bitmap of the JPEG
                        Bitmap frame = BitmapFactory.decodeByteArray(jpegData, 0, jpegData.length);

                        if (frame != null) {
                            // Do something with frame,e.g. pass to ZXING for barcode analysis.

                        }
                    }
                });

            }

        });
    }
}
