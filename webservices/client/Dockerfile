FROM kennethreitz/pipenv
COPY . /app
RUN python manage.py makemigrations
RUN python manage.py migrate
CMD ['gunicorn', 'client.wsgi', '-b', '0.0.0.0:8000']