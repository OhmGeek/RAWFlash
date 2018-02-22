from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.contrib.auth import login, authenticate
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.decorators import login_required
from .forms import SignUpForm

def index(request):
    return render(request, 'homepage/index.html')

def display_image_picker(request):
    return render(request, 'homepage/image_picker.html')

def register(request):
    if(request.method == 'POST'):
        form = SignUpForm(request.POST)
        print("POST")
        if form.is_valid():
            print("Form valid")
            form.save()
            username = form.cleaned_data.get('username')
            raw_password = form.cleaned_data.get('password1')
            user = authenticate(username=username, password=raw_password)
            login(request, user)
            return redirect(display_image_picker)
        else:
            print("Error, not valid")
