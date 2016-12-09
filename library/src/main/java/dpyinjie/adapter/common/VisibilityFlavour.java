package dpyinjie.adapter.common;

import android.support.annotation.IntDef;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({View.VISIBLE, View.INVISIBLE, View.GONE})
@Retention(value = RetentionPolicy.SOURCE)
public @interface VisibilityFlavour {
}
