from django.shortcuts import render
from django.http import JsonResponse
from .models import Album, Image
import json
# Create your views here.
def get_albums_for_user(request):
    our_albums = Album.objects.filter(linked_user=request.user)
    data = []
    for album in our_albums:
        data.append({
            "album_name": album.name,
            "album_description": album.description
            "id": album.pk
        })

    return JsonResponse({
        "albums": data
    })

def add_album(request):
    if(request.method == "POST"):
        album_name = request.POST.get('name')
        album_desc = request.POST.get('description')
        user = request.user

        print(album_name)
        print(album_desc)
        new_album = Album(name=album_name, description=album_desc, linked_user=user)
        new_album.save()
    
    return JsonResponse({
        "err": "Use POST rather than GET"
    })
        