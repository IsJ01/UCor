from rest_framework import generics

from .serializers import UserCategorySerializer
from .models import UserCategory


class UserCategoryListView(generics.ListCreateAPIView):
    serializer_class = UserCategorySerializer
    queryset = UserCategory.objects.all()


class UserCategoryDetailView(generics.RetrieveUpdateDestroyAPIView):
    serializer_class = UserCategorySerializer
    queryset = UserCategory.objects.all()