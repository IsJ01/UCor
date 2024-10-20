FROM python
LABEL authors="IsJ01"

WORKDIR /code

COPY . /code/

EXPOSE 8006

RUN pip install -r requirements.txt
RUN python manage.py makemigrations api
RUN python manage.py migrate
CMD python manage.py runserver 0.0.0.0:8006
