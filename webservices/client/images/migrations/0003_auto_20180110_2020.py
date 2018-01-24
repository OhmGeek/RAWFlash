# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('images', '0002_album_album_description'),
    ]

    operations = [
        migrations.RenameField(
            model_name='album',
            old_name='album_description',
            new_name='description',
        ),
    ]
