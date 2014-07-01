package thomascorfield.fr.mydeezer.fetcher;

import android.graphics.Bitmap;

public interface OnImageReceivedListener {

    void onImageReceived(Bitmap bitmap, Exception e);
}
