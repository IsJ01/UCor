# Generated by Django 5.1 on 2025-03-04 16:44

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0006_alter_label_chart_alter_value_chart'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='report',
            name='chart',
        ),
        migrations.AddField(
            model_name='chart',
            name='report',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='api.report'),
        ),
    ]
