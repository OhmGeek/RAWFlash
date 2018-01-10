# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('images', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='album',
            name='album_description',
            field=models.CharField(max_length=300, default=''),
            preserve_default=False,
        ),
    ]
