package com.bumptech.glide;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.newspaperdirect.example.samplesdk.SampleAppGlideModule;
import com.newspaperdirect.pressreader.android.core.graphics.glide.PRLibraryGlideModule;
import com.newspaperdirect.sdk.graphics.glide.SdkLibraryGlideModule;
import java.util.Collections;
import java.util.Set;

@SuppressWarnings("deprecation")
final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule {
  private final SampleAppGlideModule appGlideModule;

  public GeneratedAppGlideModuleImpl(Context context) {
    appGlideModule = new SampleAppGlideModule();
    if (Log.isLoggable("Glide", Log.DEBUG)) {
      Log.d("Glide", "Discovered AppGlideModule from annotation: com.newspaperdirect.example.samplesdk.SampleAppGlideModule");
      Log.d("Glide", "Discovered LibraryGlideModule from annotation: com.newspaperdirect.pressreader.android.core.graphics.glide.PRLibraryGlideModule");
      Log.d("Glide", "Discovered LibraryGlideModule from annotation: com.newspaperdirect.sdk.graphics.glide.SdkLibraryGlideModule");
    }
  }

  @Override
  public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
    appGlideModule.applyOptions(context, builder);
  }

  @Override
  public void registerComponents(@NonNull Context context, @NonNull Glide glide,
      @NonNull Registry registry) {
    new PRLibraryGlideModule().registerComponents(context, glide, registry);
    new SdkLibraryGlideModule().registerComponents(context, glide, registry);
    appGlideModule.registerComponents(context, glide, registry);
  }

  @Override
  public boolean isManifestParsingEnabled() {
    return appGlideModule.isManifestParsingEnabled();
  }

  @Override
  @NonNull
  public Set<Class<?>> getExcludedModuleClasses() {
    return Collections.emptySet();
  }

  @Override
  @NonNull
  GeneratedRequestManagerFactory getRequestManagerFactory() {
    return new GeneratedRequestManagerFactory();
  }
}
