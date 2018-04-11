<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine Spring MVC</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet"> 
        <link href="https://fonts.googleapis.com/css?family=Bungee+Shade" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <!-- Beginning of Entire Container -->
            <div class="row">
                <h1 id="VMHeader">
                    <center>Vending Machine</center>
                </h1>
                <ul class="list-group" id="errorMessages"></ul>
                <hr/>
                <!-- Beginning of Left Side of Main Container -->
                <div class="col-md-9">
                    <div class="leftMainDiv" id="leftMain">

                        <div class="form-group">
                            <div class="VMItems" id="VMItems">
                                <c:forEach var="currentVMItem" items="${itemInventory}">
                                    <div class="item">
                                        <form class="form" action="chooseItem/${currentVMItem.locationItem}" method="POST">
                                            <button class="boxVMItem col-md-4 row row-eq-height" type="submit" id="boxVMItem">
                                                <p>
                                                <div class="boxId" id="boxId"> ${currentVMItem.locationItem}</div>
                                                <div class="boxName" id="boxName">${currentVMItem.nameOfItem}</div>
                                                <div class="boxPrice" id="boxPrice">$ ${currentVMItem.costOfItem}</div>
                                                <div class="boxQuantity" id="boxQuantity"> Quantity: ${currentVMItem.numOfItems}</div>

                                                </p> </button>
                                        </form>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>


                </div>



                <!-- Beginning of Right Side of Main Container -->
                <div class="col-md-3 ">

                    <!-- Top Row of Right Main-->
                    <center>
                        <div class="col-md-12">
                            <div class="form form-horizontal">
                                <div class="form form-group">

                                    <div class="rightMain " id="rightMain ">
                                        <div class="col-md-12">
                                            <h2> Total $ In </h2>

                                            <input type="text" class="form-control addMoney input-lg" id="deposit-money-form" value="$${userMoney}">


                                            </input>

                                        </div>
                                        <div class="form form-group" id="form-group-top-buttons">

                                            <div class="col-md-6">
                                                <form class= "form" action="addDollar" method="POST" role="form" id="add-dollar">
                                                    <button type="submit" id="add-dollar-button" class="btn btn-primary btn-block">
                                                        Add Dollar
                                                    </button>
                                                </form>
                                            </div>
                                            <div class="col-md-6">
                                                <form class= "form" action="addQuarter" method="POST" role="form" id="add-quarter">
                                                    <button type="submit" id="add-quarter-button" class="btn btn-primary btn-block">
                                                        Add Quarter
                                                    </button>
                                                </form>
                                            </div>

                                            <div class="col-md-6">
                                                <form class= "form" action="addDime" method="POST" role="form" id="add-dime">
                                                    <button type="submit" id="add-dime-button" class="btn btn-primary btn-block">
                                                        Add Dime
                                                    </button>
                                                </form>
                                            </div>

                                            <div class="col-md-6">
                                                <form class= "form" action="addNickel" method="POST" role="form" id="add-nickel">
                                                    <button type="submit" id="add-nickel-button" class="btn btn-primary btn-block">
                                                        Add Nickel
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <hr/>

                                    <div class="col-md-12">
                                        <h2> Messages </h2>
                                        <textarea type="text" class="form form-control input-lg" id="edit-messages-form" placeholder="">${transactionMessage}</textarea>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="col-md-6">
                                            <h2 id="itemBlock"> Item: </h2>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="text" class="form form-control input-lg" id="edit-item-form" placeholder="Item #" value=${userSelection}>

                                            </input>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <form class="form" action="purchaseItem" method="POST">
                                            <button type="submit" id="purchase-button" class="btn btn-primary btn-block ">
                                                Make Purchase
                                            </button>
                                        </form>
                                    </div>
                                </div>

                                <hr/>

                                <div class="col-md-12">
                                    <h2> Change </h2>
                                </div>

                                <div class="col-md-12">
                                    <textarea type="text" class="form form-control input-lg" id="edit-change-form">${transactionChange}</textarea>
                                </div>


                                <div class="col-md-12">
                                    <form class="form" action="returnChange" method="POST">
                                        <button type="submit" id="change-return-button" class="btn btn-primary btn-block ">
                                            Change Return
                                        </button>
                                    </form>
                                </div>
                            </div>

                        </div>
                </div>
            </div>
        </div>
        <hr/>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


    </body>
</html>

