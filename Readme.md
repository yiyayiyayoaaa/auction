#基于WEB的网上拍卖系统
###技术选型
    * 服务器：阿里云ECS+Tomcat7
    * 后端开发语言：Java
    * 后端开发框架：SpringMVC+Spring+MyBatis
    * 数据库：Mysql 5.7
    * 项目管理工具：Maven
    * 版本控制工具：Git
    * 后台UI：jQuery EasyUI 1.5.1
    * 前台UI：Bootstrap3
    * 开发工具：IntelliJ IDEA
###需求分析
	本系统主要分为2大模块：
	* 用户模块：用户模块是面对拍卖者的，该模块的主要功能分为：登录、注册、退出、我的拍卖、个人信息，充值等。
	    > 注册：用户进入注册页面，填写完全相关信息，点击注册即可，但此时账号还未激活，系统会发送一条激活邮件至用户的注册邮箱中，用户点击邮件内的链接即可激活账号。
	    > 登录：用户无需登录即可进入首页，可以查看拍卖品的信息，但不能进行拍卖操作，当进行拍卖等操作时会跳入登录页面，用户填写相关信息即可登录。
	    > 我的拍卖：该功能主要是查看自己正在参与的拍卖，以及曾经的拍卖纪录。
	    > 个人信息：主要是显示自己个人信息，以及可以对相关信息进行修改。
	    > 充值：
	* 后台管理模块：后台管理模块面向管理员，该模块的主要功能分为：登录、商品管理、用户管理、拍卖信息管理、管理员管理等。
	    > 登录：管理员必须登录才能进行操作。账号密码由超级管理员提供，没有注册功能。
	    > 用户管理：普通管理员只拥有查询用户权限，超级管理员能够对用户进行增删改查操作。
	    > 商品管理：普通管理员只拥有商品的增加、查询和修改权限，超级管理员能够对商品进行增删改查操作。
	    > 订单管理：普通管理员
###数据模型
	商品 Commodity
<table>
    <tr>
        <th>Field Name</th>
        <th>Datatype</th>
        <th>Len</th>
        <th>PK?</th>
        <th>Not Null?</th>
        <th>Comment</th>
    </tr>
	<tr>
		<td align="center">id</td>
		<td align="center">int</td>
		<td align="center"></td>
		<td align="center">√</td>
		<td align="center">√</td>
		<td align="center">商品ID</td>
	</tr>
	<tr>
		<td align="center">cname</td>
		<td align="center">varchar</td>
		<td align="center">50</td>
		<td align="center"></td>
		<td align="center">√</td>
		<td align="center">商品名</td>
	</tr>
	<tr>
		<td align="center">description</td>
		<td align="center">varchar</td>
		<td align="center">255</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">商品描述</td>
	<tr>
		<td align="center">tid</td>
		<td align="center">int</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">√</td>
		<td align="center">商品分类 外键：CommodityType.id</td>
	<tr>
		<td align="center">startingPrice</td>
		<td align="center">double</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">√</td>
		<td align="center">商品底价</td>
	<tr>
		<td align="center">increments</td>
		<td align="center">double</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">√</td>
		<td align="center">出价增幅</td>
	<tr>
		<td align="center">appraisedPrice</td>
		<td align="center">double</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">商品估价</td>
	<tr>
		<td align="center">deposit</td>
		<td align="center">double</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">√</td>
		<td align="center">保证金</td>
    </tr>
	<tr>
		<td align="center">reservePrice</td>
		<td align="center">double</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">保留价</td>
	</tr>
	<tr>
		<td align="center">hammerPrice </td>
		<td align="center">double</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">商品成交价</td>
	</tr>
	<tr>
		<td align="center">status</td>
		<td align="center">int</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">√</td>
		<td align="center">商品状态0默认 1待拍卖 2拍卖中 3成交 4流拍</td>
	</tr>
	<tr>
		<td align="center">startTime</td>
		<td align="center">date</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">起拍时间</td>
	</tr>
	<tr>
		<td align="center">endTime</td>
		<td align="center">date</td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center"></td>
		<td align="center">结束时间</td>
	</tr>
	
</table>
 
    商品分类 CommodityType
<table>
    <tr>
        <th>Field Name</th>
        <th>Datatype</th>
        <th>Len</th>
        <th>PK?</th>
        <th>Not Null?</th>
        <th>Comment</th>
    </tr>
    <tr>
        <td align="center">id</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">√</td>
        <td align="center">分类ID</td>
    </tr>
    <tr>
        <td align="center">typeName</td>
        <td align="center">varchar</td>
        <td align="center">50</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">分类名称</td>
    </tr>
    <tr>
        <td align="center">description</td>
        <td align="center">varchar</td>
        <td align="center">255</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">分类描述</td>
    </tr>
</table>

	商品-图片 Commodity_Image  备注：一个商品有多张展示图片，商品与图片时一对多的关系。
<table>
    <tr>
        <th>Field Name</th>
        <th>Datatype</th>
        <th>Len</th>
        <th>PK?</th>
        <th>Not Null?</th>
        <th>Comment</th>
    </tr>
    <tr>
        <td align="center">id</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">√</td>
        <td align="center">ID</td>
    </tr>
    <tr>
        <td align="center">cid</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">商品ID 外键：Commodity.id</td>
    </tr>
    <tr>
        <td align="center">imgUrl</td>
        <td align="center">varchar</td>
        <td align="center">50</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">图片路径</td>
    </tr>
    <tr>
        <td align="center">description</td>
        <td align="center">varchar</td>
        <td align="center">255</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">图片描述</td>
     </tr>
</table>
    
    商品-用户 Commodity_User  备注：该表表示商品与参与该商品拍卖的用户的关系。 一个商品可以有多个用户参与，一个用户可以参与多个商品的拍卖。
<table>
        <tr>
            <th>Field Name</th>
            <th>Datatype</th>
            <th>Len</th>
            <th>PK?</th>
            <th>Not Null?</th>
            <th>Comment</th>
        </tr>
        <tr>
            <td align="center">id</td>
            <td align="center">int</td>
            <td align="center"></td>
            <td align="center">√</td>
            <td align="center">√</td>
            <td align="center">ID</td>
        </tr>
        <tr>
            <td align="center">cid</td>
            <td align="center">int</td>
            <td align="center"></td>
            <td align="center"></td>
            <td align="center">√</td>
            <td align="center">商品ID</td>
        </tr>
        <tr>
            <td align="center">uid</td>
            <td align="center">int</td>
            <td align="center"></td>
            <td align="center"></td>
            <td align="center">√</td>
            <td align="center">用户ID</td>
        </tr>
</table>
    
	订单 Order   备注：由于拍卖的特性，所以设计的订单与商品是 一对一的关系。
<table>
    <tr>
        <th>Field Name</th>
        <th>Datatype</th>
        <th>Len</th>
        <th>PK?</th>
        <th>Not Null?</th>
        <th>Comment</th>
    </tr>
    <tr>
        <td align="center">id</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">√</td>
        <td align="center">订单ID</td>
    </tr>
    <tr>
        <td align="center">orderNum</td>
        <td align="center">varchar</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">订单号</td>
    </tr>
    <tr>
        <td align="center">cid</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">商品ID 外键：Commodity.id</td>
    </tr>
    <tr>
        <td align="center">address</td>
        <td align="center">varchar</td>
        <td align="center">255</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">地址</td>
    </tr>
    <tr>
        <td align="center">totalPrice</td>
        <td align="center">double</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">总价</td>
    </tr>
    <tr>
        <td align="center">payTime</td>
        <td align="center">datetime</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">支付时间</td>
    </tr>
    <tr>
        <td align="center">finishTime</td>
        <td align="center">datetime</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">完成时间</td>
    </tr>
    <tr>
        <td align="center">status</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">订单状态 0待支付，1已支付，2已发出，3已完成，4已取消</td>
    </tr>
</table>

    订单-用户 Order_User    备注：一个用户可以有多个订单，用户与订单之间是一对多的关系
<table>
    <tr>
        <th>Field Name</th>
        <th>Datatype</th>
        <th>Len</th>
        <th>PK?</th>
        <th>Not Null?</th>
        <th>Comment</th>
    </tr>
    <tr>
        <td align="center">id</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">√</td>
        <td align="center">ID</td>
    </tr>
    <tr>
        <td align="center">oid</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">订单ID 外键：Order.id</td>
    </tr>
    <tr>
        <td align="center">uid</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">用户ID 外键：User.id</td>
    </tr>
</table>

    用户 User
<table>
    <tr>
        <th>Field Name</th>
        <th>Datatype</th>
        <th>Len</th>
        <th>PK?</th>
        <th>Not Null?</th>
        <th>Comment</th>
    </tr>
    <tr>
        <td align="center">id</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">√</td>
        <td align="center">用户ID</td>
    </tr>
    <tr>
        <td align="center">username</td>
        <td align="center">varchar</td>
        <td align="center">18</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">用户名</td>
    </tr>
    <tr>
        <td align="center">portrait</td>
        <td align="center">varchar</td>
        <td align="center">50</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">头像</td>
    </tr>
    <tr>
        <td align="center">password</td>
        <td align="center">varchar</td>
        <td align="center">18</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">密码</td>
    </tr>
    <tr>
         <td align="center">nickname</td>
         <td align="center">varchar</td>
         <td align="center">20</td>
         <td align="center"></td>
         <td align="center">√</td>
         <td align="center">昵称</td>
     </tr>
    <tr>
         <td align="center">sex</td>
         <td align="center">varchar</td>
         <td align="center">2</td>
         <td align="center"></td>
         <td align="center">√</td>
         <td align="center">性别</td>
    <tr>
         <td align="center">birth</td>
         <td align="center">date</td>
         <td align="center"></td>
         <td align="center"></td>
         <td align="center">√</td>
         <td align="center">出生日期</td>
    <tr>
         <td align="center">phone</td>
         <td align="center">varchar</td>
         <td align="center">20</td>
         <td align="center"></td>
         <td align="center">√</td>
         <td align="center">手机号码</td>
    <tr>
         <td align="center">email</td>
         <td align="center">varchar</td>
         <td align="center">30</td>
         <td align="center"></td>
         <td align="center">√</td>
         <td align="center">邮箱</td>
     </tr>
    <tr>
         <td align="center">account</td>
         <td align="center">double</td>
         <td align="center"></td>
         <td align="center"></td>
         <td align="center">√</td>
         <td align="center">账户，单位：元，默认为0.0</td>
     </tr>
    
</table>

    用户-地址 User_Address  备注：一个用户可以设置多个地址，其中一个地址为默认地址
<table>
    <tr>
        <th>Field Name</th>
        <th>Datatype</th>
        <th>Len</th>
        <th>PK?</th>
        <th>Not Null?</th>
        <th>Comment</th>
    </tr>
    <tr>
        <td align="center">id</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">√</td>
        <td align="center">ID</td>
    </tr>
    <tr>
        <td align="center">uid</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">用户ID 外键：User.id</td>
    </tr>
    <tr>
        <td align="center">address</td>
        <td align="center">varchar</td>
        <td align="center">255</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">用户收货地址</td>
    <tr>
        <td align="center">status</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">是否默认地址，1是，2不是</td>
    </tr>
</table>

    管理员 Admin
<table>
    <tr>
        <th>Field Name</th>
        <th>Datatype</th>
        <th>Len</th>
        <th>PK?</th>
        <th>Not Null?</th>
        <th>Comment</th>
    </tr>
    <tr>
        <td align="center">id</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">√</td>
        <td align="center">管理员ID</td>
    </tr>
    <tr>
        <td align="center">username</td>
        <td align="center">varchar</td>
        <td align="center">18</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">管理员用户名</td>
    </tr>
    <tr>
        <td align="center">password</td>
        <td align="center">varchar</td>
        <td align="center">18</td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">管理员密码</td>
    </tr>
    <tr>
        <td align="center">status</td>
        <td align="center">int</td>
        <td align="center"></td>
        <td align="center"></td>
        <td align="center">√</td>
        <td align="center">管理员类型 1超级管理员 ，2普通管理员</td>
    </tr>
</table>



	
	
	

	
	