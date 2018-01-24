from django.shortcuts import render
from django.http import JsonResponse
from .models import Album, Image
# Create your views here.
def get_albums_for_user(request):
    our_albums = Album.objects.filter(linked_user=request.user)
    data = []
    for album in our_albums:
        data.append({
            "album_name": album.name,
            "album_description": album.description
        })

    return JsonResponse({
        "albums": data
    })
