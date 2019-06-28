/*
 * Copyright 2016 Stephane Nicolas
 * Copyright 2016 Daniel Molinero Reguerra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package toothpick.smoothie.provider;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import javax.inject.Provider;

public class SharedPreferencesProvider implements Provider<SharedPreferences> {
  Application application;
  String preferencesName;

  public SharedPreferencesProvider(Application application) {
    this(application, null);
  }

  public SharedPreferencesProvider(Application application, String preferencesName) {
    this.application = application;
    this.preferencesName = preferencesName;
  }

  @Override
  public SharedPreferences get() {
    if (preferencesName != null) {
      return application.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
    }
    return PreferenceManager.getDefaultSharedPreferences(application);
  }
}
