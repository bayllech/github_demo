<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Left side column. contains the sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">

            <li class="header">业务模块</li>
            <li class="treeview ${param.menu == 'business_device_rent' ? 'active' : ''}">
                <a href="/device/rent">
                    <i class="fa fa-edit"></i> <span>设备租赁</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/device/setting/new"><i class="fa fa-circle-o"></i> 新增设备</a></li>
                    <li><a href="/device/rent/new"><i class="fa fa-circle-o"></i> 新增设备租赁合同</a></li>
                </ul>
            </li>
            <li class="treeview ${param.menu == 'work_out_rent' ? 'active' : ''}">
                <a href="/workOut/rent">
                    <i class="fa fa-blind"></i> <span>劳务派遣</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/workOut/workType"><i class="fa fa-circle-o"></i> 新增工种</a></li>
                    <li><a href="/workOut/rent/add"><i class="fa fa-circle-o"></i> 新增劳务派遣合同</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-jsfiddle"></i> <span>网盘系统</span>
                </a>
            </li>
                <li class="header">财务模块</li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-pie-chart"></i> <span>财务报表</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                </li>
            <%--<shiro:hasRole name="role_admin">
                <li class="header">设置模块</li>
                <li class="treeview ${fn:startsWith(param.menu,'sys_') ? 'active' : ''}">
                    <a href="#">
                        <i class="fa fa-cogs"></i> <span>系统设置</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li class="${param.menu == 'sys_accounts' ? 'active' : ''}">
                            <a href="/user"><i class="fa fa-circle-o"></i> 账户管理</a>
                        </li>
                        <li class="${param.menu == 'sys_device' ? 'active' : ''}">
                            <a href="/setting/device"><i class="fa fa-circle-o"></i> 设备管理</a>
                        </li>
                    </ul>
                </li>
            </shiro:hasRole>--%>
            <li class="header">用户模块</li>
            <li class="treeview">
                <a href="/logout">
                    <i class="fa fa-sign-out"></i> <span>安全退出</span></i>
                </a>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
