# Online Shopping Commerce Sample

![samplemicroservices](https://github.com/philipdaquin/online-shopping-services/assets/85416532/c84079a8-7f39-4ed1-bb15-9b62b3fafe04)
Terraform Dependencies 
- https://github.com/philipdaquin/infra-modules
- https://github.com/philipdaquin/infra-live
### General System Requirements
1. Users should be able to add new products to sell 
2. Users should be ablet to search by their name or category 
3. Users can search and view all the products, but they will have to become a registered member to buy a product 
4. Users should be able to add / remove / modify product items in their shopping cart 
5. Users can check out and buy items in the shopping cart 
6. Users can rate and add a review for a product 
7. The should be able to specify a shipping address where their order will be delivered 
8. Users can cancel an order if it has not shipped 
9. Users should be able to pay through credit cards or electronic bank transfers 
10. Users should be able to track their shipment to see the current state of their order 
11. Users should get notifications whenever there is a change in the order or shipping status

### Main Usage
- Add/ Delete/ edit, rate and add review on products; search by their name and category
- Check out/ modify, add new items and delete items in their shopping cart
- Place/ cancel/ track theirs order
- Payment system with credit or bank transfers
- Guests can only view, Users can only buy new products, vendors can sell products
- System will notify users if there is any changes in the order or shipping

### Users and Roles
- Customers: Can view and place an order on any products, and add new products to sell
- Anonymous customers (prospective buyers): can only view products and add to their shopping cart, as well as become members
- Admin: Responsible for account management, adding and modifying product categories
- Vendors: Add new items to sell
- System : notifies any significant changes on the user’s orders or payments

### Scaling Goals
- Highly available: We need to make sure that we can handle random spikes in traffic during peak hours
- Data privacy: We need to make sure their shipping address is concealed

### Performance Goals
- Moderate latency: We want to be able to see the products with at least minimal delay when the user arrive on the front page. The faster the user can reach to placing an order from the start to end, the better it is
- Highly consistent

### Cost Constraints
- Minimise the maintenance: We want a reliable system that can handle random spikes in our system

### Data Relations 
![relations](https://github.com/philipdaquin/online-shopping-services/assets/85416532/2d9a3fb4-6472-49a5-9e36-4d51d17c3604)



