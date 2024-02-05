from django.urls import path
from . import views

urlpatterns = [
    path('', views.login, name="home"),
    path('login_action', views.login_action, name="home"),
    path('admin_home',views.admin_home, name="admin_home"),
    path('registered_verify/', views.registered_verify, name="registered_verify"),
    path('accept/<int:accept_id>', views.accept, name="accept"),
    path('reject/<int:reject_id>', views.reject, name="reject"),
    path('change_password', views.change_password, name="change_password"),
    path('post_password', views.post_password, name ="post_password"),
    path('complaints_reply/', views.complaints_reply, name="complaints_reply"),
    path('reply/<int:r_id>', views.reply, name="reply"),
    path('post_reply', views.post_reply, name="post_reply"),
    path('products', views.products, name="products"),
    path('delete/<int:p_id>', views.delete, name="delete"),



# user url

    path('login_code', views.login_code, name="login_code"),
    path('registration', views.registration, name="registration"),
    path('add_product', views.add_product, name="add_product"),
    path('edit_product_view', views.edit_product_view, name='edit_product_view'),
    path('edit_product', views.edit_product, name="edit_product"),
    path('delete_product', views.delete_product, name="delete_product"),
    path('send_complaint', views.send_complaint, name="send_complaint"),
    path('complaint_reply', views.complaint_reply, name="complaint_reply"),
    path('view_product', views.view_product, name="view_product"),
    path('view_profile', views.view_profile, name="view_profile"),
    path('view_all_product', views.view_all_product, name="view_all_product"),
    path('change_password', views.change_password, name="change_password"),
    path('changing_password', views.changing_password, name="changing_password"),
    path('in_message2', views.in_message2, name="in_message2"),
    path('view_message2', views.view_message2, name="view_message2"),
    path('view_users_for_chat', views.view_users_for_chat, name="view_users_for_chat"),
    path("product_request", views.product_request, name="product_request"),
    path("view_product_request", views.view_product_request, name = "view_product_request"),
    path("verify_users", views.verify_users, name="verify_users"),
    path("add_verify_slot", views.add_verify_slot, name="add_verify_slot"),
    path("view_borrowed_products", views.view_borrowed_products, name="view_borrowed_products"),


]
