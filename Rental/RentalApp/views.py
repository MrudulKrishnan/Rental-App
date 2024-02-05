import datetime

from django.contrib import auth
from django.contrib.auth.decorators import login_required
from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.db import connection
import json
from django.http import JsonResponse


from RentalApp.models import *


# Create your views here.


def login(request):
    return render(request, "login_index.html")


def logout(request):
    auth.logout(request)
    return render(request, "login_index.html")


def login_action(request):
    try:
        username = request.POST['Username']
        password = request.POST['Password']
        obj = Login.objects.get(Username=username, Password=password)
        if obj.Type == 'admin':
            auth_obj = auth.authenticate(Username='admin', Password='admin')
            if auth_obj is not None:
                auth.login(request, auth_obj)
            request.session['a_id'] = obj.id
            return redirect('admin_home')
    except:
        return HttpResponse('''<script>alert ("incorrect username or password");window.location="/"</script>''')



@login_required(login_url='/')
def admin_home(request):
    return render(request, "admin home.html")


@login_required(login_url='/')
def registered_verify(request):
    user_obj = User.objects.all()
    return render(request, "View registered user and verify.html", {'user': user_obj})


@login_required(login_url='/')
def accept(request, accept_id):
    user_obj = Login.objects.get(id=accept_id)
    user_obj.Type = "user"
    user_obj.save()
    return HttpResponse('''<script> alert("accepted");window.location="/registered_verify#about"</script>''')


@login_required(login_url='/')
def reject(request, reject_id):
    user_obj = Login.objects.get(id=reject_id)
    user_obj.Type = "rejected"
    user_obj.save()
    return HttpResponse('''<script>alert("rejected");window.location="/registered_verify#about"</script>''')


@login_required(login_url='/')
def change_password(request):
    obj = Login.objects.get(id=request.session['a_id'])
    return render(request, "change password.html", {'obj': obj})


@login_required(login_url='/')
def post_password(request):
    newpassword = request.POST['Newpassword']
    cpassword = request.POST['Cpassword']
    if newpassword == cpassword:
        obj = Login.objects.get(id=request.session['a_id'])
        obj.Password = cpassword
        obj.save()
        return HttpResponse('''<script>alert("password updated");window.location="/admin_home"</script>''')
    else:
        return HttpResponse('''<script>alert("password not matched");window.location="/change_password"</script>''')


@login_required(login_url='/')
def complaints_reply(request):
    obj = Complaint.objects.all()
    return render(request, "view complaint and send reply.html", {'obj': obj})


@login_required(login_url='/')
def reply(request, r_id):
    request.session['r_id'] = r_id
    return render(request, "reply.html")


@login_required(login_url='/')
def post_reply(request):
    obj = Complaint.objects.get(id=request.session['r_id'])
    reply = request.POST['reply_f']
    obj.Reply = reply
    obj.save()
    return HttpResponse('''<script>alert("replied");window.location="/complaints_reply#about"</script>''')


@login_required(login_url='/')
def products(request):
    obj = Product.objects.all()
    return render(request, "view_product.html", {'obj': obj})


@login_required(login_url='/')
def delete(request,p_id):
    obj = Product.objects.get(id=p_id)
    obj.delete()
    return redirect('products')

#  /////////////////////////////webservice////////////////////////////////////////////


from django.core import serializers





def login_code(request):
    username = request.POST['username']
    password = request.POST['password']
    try:
        users = Login.objects.get(Username=username, Password=password)
        if users is None:
            data = {"task": "invalid"}

        else:
            request.session['user_id'] = users.id
            data = {"task": "valid", "id": users.id}
            r = json.dumps(data)
            return HttpResponse(r)
    except:
        data = {"task": "invalid"}
        r = json.dumps(data)
        print(r)
        return HttpResponse(r)


def registration(request):
    firstname = request.POST['Firstname']
    lastname = request.POST['Lastname']
    place = request.POST['Place']
    post_office = request.POST['Post']
    pin_code = request.POST['Pin']
    phone = request.POST['Phone']
    email_id = request.POST['Email']
    proof = request.FILES['proof']
    fss = FileSystemStorage()
    proof_file = fss.save(proof.name, proof)
    username = request.POST['Username']
    password = request.POST['Password']

    lob = Login()
    lob.Username = username
    lob.Password = password
    lob.Type = 'pending'
    lob.save()

    user_obj = User()
    user_obj.Firstname = firstname
    user_obj.Lastname = lastname
    user_obj.Place = place
    user_obj.Post = post_office
    user_obj.Pin = pin_code
    user_obj.Phone = phone
    user_obj.Email = email_id
    user_obj.Proof = proof_file
    user_obj.LID = lob
    user_obj.save()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)


def view_profile(request):
    user_id = request.POST['uid']
    user_obj = User.objects.filter(LID_id=user_id)
    user_data = []

    for i in user_obj:
        data = {'Firstname': i.Firstname, 'Lastname': i.Lastname, 'Place': i.Place,
                'Post': i.Post, 'Pin': i.Pin, 'Phone': i.Phone, 'Email': i.Email,
                'Proof': str(i.Proof.url)}
        user_data.append(data)
    r = json.dumps(user_data)
    return HttpResponse(r)


def add_product(request):
    print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
    user_id = request.POST['lid']
    product = request.POST["product_name"]
    type = request.POST["product_type"]
    details = request.POST["product_details"]
    price_per_day = request.POST["price_per_day"]
    image = request.FILES["image"]
    fss = FileSystemStorage()
    img_file = fss.save(image.name, image)
    adding = Product()
    adding.Product_name = product
    adding.Product_type = type
    adding.Details = details
    adding.Price_per_day = price_per_day
    adding.image = img_file
    adding.UID = User.objects.get(LID_id=user_id)
    adding.save()
    data = {"task": "success"}
    r = json.dump(data)
    return HttpResponse(r)


def view_product(request):
    # print("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRR")
    user_id = request.POST['lid']
    # print("##################################################", user_id)
    product_obj = Product.objects.filter(UID__LID_id=user_id)
    product_data = []

    for i in product_obj:
        data = {'Product': i.Product_name, 'Type': i.Product_type, 'Details': i.Details,
                'Price_per_day': i.Price_per_day, 'Image': str(i.image.url), 'product_id': i.id}
        product_data.append(data)
    r = json.dumps(product_data)
    return HttpResponse(r)


def view_all_product(request):
    user_id =request.POST['lid']
    product_obj = Product.objects.exclude(UID__LID=user_id)
    product_data = []
    for i in product_obj:
        data = {'Product': i.Product_name, 'Type': i.Product_type, 'Details': i.Details,
                'Price_per_day': i.Price_per_day, 'Image': str(i.image.url), 'product_id': i.id}
        product_data.append(data)
    r = json.dumps(product_data)
    return HttpResponse(r)


def edit_product_view(request):
    product_id = request.POST["p_id"]
    product_obj = Product.objects.filter(id=product_id)
    product_data = []

    for i in product_obj:
        data = {'product': i.Product_name, 'Type': i.Product_type, 'Details': i.Details,
                'Price_per_day': i.Price_per_day, 'Image': str(i.image.url)}
        product_data.append(data)
    print(product_data)
    # r = json.dump(product_data )
    return JsonResponse(product_data, safe=False)


def edit_product(request):
    product_id = request.POST["p_id"]
    product_obj = Product.objects.get(id=product_id)
    product = request.POST["product_name"]
    type = request.POST["product_type"]
    details = request.POST["product_details"]
    price_per_day = request.POST["price_per_day"]
    image = request.FILES["image"]
    fss = FileSystemStorage()
    img_file = fss.save(image.name, image)
    product_obj.Product_name = product
    product_obj.Product_type = type
    product_obj.Details = details
    product_obj.Price_per_day = price_per_day
    product_obj.image = img_file
    product_obj.save()
    data = {"task": "success"}
    # r = json.dump(data)
    return JsonResponse(data, safe=False)


def delete_product(request):
    product_id = request.POST["p_id"]
    product_obj = Product.objects.get(id=product_id)
    product_obj.delete()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)


def send_complaint(request):
    complaints = request.POST["complaint"]
    u_id = request.POST["lid"]

    reply1 = "waiting"
    complaint_obj = Complaint()
    complaint_obj.Complaint1 = complaints
    complaint_obj.Date = datetime.datetime.now().strftime("%d/%m/%y")
    complaint_obj.Reply = reply1
    complaint_obj.UID = User.objects.get(LID_id=u_id)
    complaint_obj.save()
    data = {'task': 'success'}
    r = json.dumps(data)
    return HttpResponse(r)


def complaint_reply(request):
    user_id = request.POST['lid']
    complaint_obj = Complaint.objects.filter(UID__LID__id=user_id)
    data = []
    for i in complaint_obj:
        row = {'Complaint': i.Complaint1, 'Reply': i.Reply, 'Date': str(i.Date)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def changing_password(request):
    login_id = request.POST['cid']
    print("444$$$$$$$$$$$$$$$$$$$$$$$", login_id)
    current_password = request.POST['current_password']
    confirm_password = request.POST['confirm_password']
    new_password = request.POST['new_password']
    login_obj = Login.objects.get(id=login_id)
    password = login_obj.Password
    print("current password", current_password)
    print("confirm password", confirm_password)
    print("new password", new_password)
    if password == current_password and new_password == confirm_password:
        login_obj.Password = confirm_password
        login_obj.save()
        data = {'task': 'success'}
        r = json.dumps(data)
        return HttpResponse(r)
    else:
        data = {'task': 'invalid'}
        r = json.dumps(data)
        return HttpResponse(r)


def product_request(request):
    login_id = request.POST['lid']
    p_id = request.POST['p_id']
    user_id = User.objects.get(LID_id=login_id)
    product_id = Product.objects.get(id=p_id)
    request_add = Request()
    request_add.UID = user_id
    request_add.PID = product_id
    request_add.ReturnDate = "waiting..."
    request_add.Status = "Pending"
    request_add.Date = datetime.datetime.now().strftime("%d/%m/%y")
    request_add.save()
    data = {"task": "success"}
    return JsonResponse(data, safe=False)


def view_product_request(request):
    login_id = request.POST["lid"]
    user_obj = User.objects.get(LID_id=login_id)
    request_obj = Request.objects.filter(UID_id=user_obj.id)
    data = []
    for i in request_obj:
        row = {'date': str(i.Date), 'status': i.Status, 'return_date': str(i.ReturnDate), 'product_name': i.PID.Product_name,
               'product_type': i.PID.Product_type, 'product_details': i.PID.Details, 'price_per_day': i.PID.Price_per_day,
               'image': str(i.PID.image.url)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def verify_users(request):
    login_id = request.POST["lid"]
    request_obj = Request.objects.filter(PID__UID__LID__id=login_id, Status='pending')
    data = []
    for i in request_obj:
        row = {"rid": i.id,'Firstname': i.UID.Firstname, 'Lastname': i.UID.Lastname, 'Place': i.UID.Place, 'Post': i.UID.Post,
               'Pin': i.UID.Pin, 'Phone': i.UID.Phone, 'Email': i.UID.Email, 'Proof': str(i.UID.Proof.url),
               'product_name': i.PID.Product_name, 'product_type': i.PID.Product_type,
               'product_details': i.PID.Details, 'price_per_day': i.PID.Price_per_day, 'image': str(i.PID.image.url)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def add_verify_slot(request):
    print(request.POST)
    ReturnDate = request.POST["ReturnDate"]
    r_id = request.POST["rid"]
    request_obj = Request.objects.get(id=r_id)
    request_obj.Status = "accept"
    request_obj.ReturnDate = ReturnDate
    request_obj.save()
    data = {"task": "success"}
    return JsonResponse(data, safe=False)


def view_borrowed_products(request):
    login_id = request.POST["lid"]
    # user_obj = User.objects.get(LID_id=login_id)
    request_obj = Request.objects.filter(UID__LID_id=login_id, Status="accept")
    data = []
    for i in request_obj:
        row = {'Borrowed_date': str(i.Date), 'Status': i.Status, 'Return_date': str(i.ReturnDate),
               'Product': i.PID.Product_name, 'Type': i.PID.Product_type, 'Details': i.PID.Details,
               'Price_per_day': i.PID.Price_per_day, 'Image': str(i.PID.image.url)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


# chat


def view_users_for_chat(request):
    user_obj = User.objects.all()
    user_data = []
    for i in user_obj:
        data = {'first_name': i.Firstname, 'last_name': i.Lastname, 'user_id': i.id}
        user_data.append(data)
    r = json.dumps(user_data)
    # return JsonResponse(worker_data)
    return HttpResponse(r)




def view_message2(request):
    print(request.POST)
    fromid = request.POST['fid']
    toid = request.POST['toid']
    mid = request.POST['lastmsgid']

    ob1 = Chat.objects.filter(From_id__LID__id=fromid, To_id__id=toid, id__gt=mid)
    ob2 = Chat.objects.filter(From_id__id=toid, To_id__LID__id=fromid, id__gt=mid)
    ob = ob1.union(ob2)
    ob = ob.order_by("id")
    data = []
    for i in ob:
        r = {"msgid": i.id, "date": str(i.Date), "message": i.Chat1, "fromid": i.From_id.id}
        data.append(r)
    if len(data) > 0:
        return JsonResponse({"status": "ok", "res1": data})
    else:
        return JsonResponse({"status": "na"})


def in_message2(request):
    print(request.POST)
    fromid = request.POST['fid']
    toid = request.POST['toid']
    message = request.POST['msg']
    chat_obj = Chat()
    chat_obj.From_id = User.objects.get(LID__id=fromid)
    chat_obj.To_id = User.objects.get(id=toid)
    chat_obj.Chat1 = message
    chat_obj.Date = datetime.datetime.now().strftime("%d-%m-%y")
    chat_obj.Time = datetime.datetime.now().strftime("%H:%M:%S")
    chat_obj.save()
    data = {"status": "send"}
    r = json.dumps(data)
    return HttpResponse(r)
