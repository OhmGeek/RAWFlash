from django.db import models
from django.contrib.auth.models import User

class Album(models.Model):
    '''An album, requiring sorting of images'''
    name = models.CharField(max_length=150)
    linked_user = models.ForeignKey(User, on_delete=models.DO_NOTHING)
    description = models.CharField(max_length=300)

class Image(models.Model):
    '''A model to represent an image, being tied to an album'''
    upload = models.FileField(upload_to='uploads/')
    linked_album = models.ForeignKey(Album, on_delete=models.CASCADE)
