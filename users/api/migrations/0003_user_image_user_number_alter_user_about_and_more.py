# Generated by Django 5.0.6 on 2024-09-07 08:46

import api.validators
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0002_alter_user_last_login'),
    ]

    operations = [
        migrations.AddField(
            model_name='user',
            name='image',
            field=models.ImageField(null=True, upload_to=''),
        ),
        migrations.AddField(
            model_name='user',
            name='number',
            field=models.CharField(blank=True, max_length=100, null=True, unique=True, verbose_name='telephone number'),
        ),
        migrations.AlterField(
            model_name='user',
            name='about',
            field=models.CharField(blank=True, max_length=1000, null=True),
        ),
        migrations.AlterField(
            model_name='user',
            name='username',
            field=models.CharField(error_messages={'unique': 'A user with that username already exists.'}, help_text='Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only.', max_length=150, unique=True, validators=[api.validators.SpaceValidator], verbose_name='username'),
        ),
    ]