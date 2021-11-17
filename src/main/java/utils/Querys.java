package utils;

public class Querys {
    public static final String SELECT_CUSTOMERS_QUERY = "select * from Customers";
    public static final String SELECT_CUSTOMER_BY_ID_QUERY = "select * from Customers where idCustomer = ?";
    public static final String SELECT_ITEMS_QUERY = "select * from Items";
    public static final String SELECT_ITEM_BY_ID_QUERY = "select * from Items where idItem = ?";
    public static final String SELECT_PURCHASE_BY_DATE_QUERY = "select idPurchase,date,C.idCustomer,C.name,C.telephone,C.address,I.idItem,I.name,I.company,I.price\n" +
            "from Purchases inner join (Customers C,Items I) on Purchases.idCustomer = C.idCustomer and Purchases.idItem = I.idItem where date = ?";
    public static final String SELECT_PURCHASES_QUERY = "select idPurchase,date,C.idCustomer,C.name,C.telephone,C.address,I.idItem,I.name,I.company,I.price\n" +
            "from Purchases inner join (Customers C,Items I) on Purchases.idCustomer = C.idCustomer and Purchases.idItem = I.idItem";
    public static final String SELECT_PURCHASES_BY_ITEM_QUERY = "select count(idItem) from Purchases where idItem = ?";
    public static final String SELECT_PURCHASES_BY_CUSTOMER_QUERY = "select idPurchase,date,C.idCustomer,C.name,C.telephone,C.address,I.idItem,I.name,I.company,I.price\n" +
            "from Purchases inner join (Customers C,Items I) on Purchases.idCustomer = C.idCustomer and Purchases.idItem = I.idItem where C.idCustomer = ? ";
    public static final String SELECT_PURCHASE_IN_REVIEW_QUERY = "select Purchases.idPurchase,Purchases.date,C.idCustomer,C.name,C.telephone,C.address,I.idItem,I.name,I.company,I.price\n" +
            "from Purchases inner join (Customers C,Items I,Reviews R ) on Purchases.idCustomer = C.idCustomer and Purchases.idItem = I.idItem and Purchases.idPurchase = R.idPurchase where R.idPurchase = ?";
    public static final String SELECT_REVIEW_QUERY = "select idReview,rating,title,description,Reviews.date,P.idPurchase,P.date,C.idCustomer,C.name,C.telephone,C.address,I.idItem,I.name,I.company,I.price\n" +
            "from Reviews inner join (Purchases P ,Customers C,Items I) on Reviews.idPurchase = P.idPurchase and P.idCustomer = C.idCustomer and P.idItem = I.idItem";
    public static final String SELECT_REVIEW_BY_ITEM_QUERY = "select idReview,rating,title,description,Reviews.date,P.idPurchase,P.date,C.idCustomer,C.name,C.telephone,C.address,I.idItem,I.name,I.company,I.price\n" +
            "from Reviews inner join (Purchases P ,Customers C,Items I) on Reviews.idPurchase = P.idPurchase and P.idCustomer = C.idCustomer and P.idItem = I.idItem where P.idItem = ?";
    public static final String SELECT_REVIEW_BY_CUSTOMER_QUERY = "select idReview,rating,title,description,Reviews.date,P.idPurchase,P.date,C.idCustomer,C.name,C.telephone,C.address,I.idItem,I.name,I.company,I.price\n" +
            "from Reviews inner join (Purchases P ,Customers C,Items I) on Reviews.idPurchase = P.idPurchase and P.idCustomer = C.idCustomer and P.idItem = I.idItem where P.idCustomer = ?";
    public static final String INSERT_CUSTOMER_QUERY ="INSERT INTO Customers (name,telephone,address) values(?,?,?)";
    public static final String INSERT_PURCHASE_QUERY = "INSERT INTO Purchases (date,idCustomer,idItem) values(?,?,?)";
    public static final String INSERT_ITEM_QUERY = "INSERT INTO Items (name,company,price) values(?,?,?)";
    public static final String INSERT_REVIEW_QUERY = "INSERT INTO Reviews (rating, title, description, date, idPurchase) values(?,?,?,?,?)";
    public static final String UPDATE_CUSTOMER_QUERY = "update Customers set name = ?, telephone = ?, address = ? where idCustomer= ? ";
    public static final String COUNT_REVIEW_BY_ITEM = "select count(Reviews.idPurchase) from Reviews join Purchases P on P.idPurchase = Reviews.idPurchase where idItem = ?";
    public static final String UPDATE_ITEM_QUERY = "update Items set name = ?, company = ?, price = ? where idItem = ?";
    public static final String UPDATE_PURCHASES_QUERY = "update Purchases set date = ? where idPurchase = ?";
    public static final String DELETE_CUSTOMER_QUERY = "DELETE from Customers where idCustomer = ?";
    public static final String DELETE_PURCHASE_QUERY = "DELETE from Purchases where idPurchase = ?";
    public static final String DELETE_PURCHASES_BY_IDITEM_QUERY = "DELETE from Purchases where idItem = ?";
    public static final String DELETE_ITEM_QUERY = "DELETE from Items where idItem = ?";



    public static final String UPDATE_REVIEW_QUERY = "update Reviews set rating = ?,title = ?,description = ?,date = ? where idReview = ?";
    public static final String DELETE_REVIEW = "delete from Reviews where idReview = ?";

}
