from django.db import models

# Create your models here.


# модель категории пользователей
class UserCategory(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100, unique=True)

    def __str__(self):
        return self.name
