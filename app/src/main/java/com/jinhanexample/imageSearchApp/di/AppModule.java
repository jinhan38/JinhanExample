package com.jinhanexample.imageSearchApp.di;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

//ApplicationComponent는 Application 전체의 생명주기를 lifetime으로 갖습니다.
// Application이 생성되는(onCreate) 시점에 함께 생성되고, Application이 파괴되는(onDestroy) 시점에 함께 파괴됩니다.
//Hilt는 표준적으로 제공하는 component 들이 이미 존재하기 때문에
// @InstallIn 어노테이션을 사용하여 표준 component에 module들을 install 할 수 있습니다.
// Hilt에서 제공하는 기본적인 규칙은 모든 module에 @InstallIn 어노테이션을 사용하여 어떤 component에 install 할지 반드시 정해주어야 합니다
@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {
}
