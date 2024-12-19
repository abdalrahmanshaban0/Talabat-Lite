#pragma once
#include <crow.h>
#include <crow/app.h>
#include <crow/json.h>
using namespace std;

void helloServer();
void authClient();
void merchantRegistration();
void customerRegistration();
void courierRegistration();
void uploadImage();
void addItem();
void retrieveItem();
void retrieveItemImage();
void deleteItem();
void getMerchantInfoHome();
void getItems();
void getMerchantData();
void changePickupAddress();
void getMerchantsSearchResults();
void getItemsSearchResults();
void getCustomerImage();
void getCustomerData();
void setCustomerData();
void addCustomerImage();
void getTopRatedMerchants();
void getCartItems();
void getCategory();
void addCustomerCard();
void customerGetItems();
void addItemToCart();
void getMerchantActiveOrders();
void getOrderDetails();
void getAccountType();
void removeCartItem();
void makeOrder();
void getCourierData();
void getCourierOrdersFromServer();
void uploadProfileImage();
void getProfileImage();
void customerGetsMerchantInfoHome();
void getProfileImageMerchId();
void saveOrder();
void deleteCart();
void getCustomerOrdersFromServer();
void merchantAcceptOrder();
void courierAcceptOrder();
