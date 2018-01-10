from django.contrib import admin
from .models import Album, Image
# Register your models here.
@admin.register(Album)
class AlbumAdmin(admin.ModelAdmin):
    fields = ('name',)

@admin.register(Image)
class ImageAdmin(admin.ModelAdmin):
    fields = ('upload', 'linked_album')
