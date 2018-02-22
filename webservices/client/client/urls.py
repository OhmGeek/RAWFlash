"""client URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.8/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Add an import:  from blog import urls as blog_urls
    2. Add a URL to urlpatterns:  url(r'^blog/', include(blog_urls))
"""
from django.urls import include, path
from django.contrib import admin
from django.contrib.auth import views as auth_views
from .views import index, register, display_image_picker

urlpatterns = [
    path('admin/', admin.site.urls),
    path('register/', register),
    path('ajax/',  include('images.urls')),
    path('dashboard/', display_image_picker),
    path('login/', auth_views.LoginView.as_view()),
    path('', index)
]
