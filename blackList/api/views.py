from rest_framework import generics

from .serializers import BlackUserSerializer
from .models import BlackUser


class BlackUserListView(generics.ListCreateAPIView):
    serializer_class = BlackUserSerializer
    queryset = BlackUser.objects.all()



class BlackUserDetailView(generics.RetrieveUpdateDestroyAPIView):
    serializer_class = BlackUserSerializer
    queryset = BlackUser.objects.all()