/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.73
 * Generated at: 2021-12-08 03:21:45 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("	<!-- header include -->\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	<!-- 메인 화면 이미지 -->\r\n");
      out.write("	<div class=\"py-5 bg-image-full\" style=\"background-image: url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/resources/images/main/main_bg.jpg');\">\r\n");
      out.write("	    <h1>Servlet/JSP를 이용한<br>Semi Project</h1>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	<!-- 내용 작성 부분 -->\r\n");
      out.write("	<section class=\"py-5\">\r\n");
      out.write("	  <div class=\"container\">\r\n");
      out.write("	    <h1>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${today}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h1>\r\n");
      out.write("	    <p class=\"lead\">로그인 회원정보</p>\r\n");
      out.write("	  	<p>\r\n");
      out.write("	  		ID : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginMember.memberId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" <br>\r\n");
      out.write("	  		Name : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginMember.memberName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" <br>\r\n");
      out.write("	  		Phone : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginMember.memberPhone}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" <br>\r\n");
      out.write("	  		E-mail: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginMember.memberEmail}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" <br>\r\n");
      out.write("	  		Address: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginMember.memberAddress}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" <br>\r\n");
      out.write("	  		\r\n");
      out.write("	  	</p>\r\n");
      out.write("	  </div>\r\n");
      out.write("	  \r\n");
      out.write("	</section>\r\n");
      out.write("	\r\n");
      out.write("	<section class=\"py-5\">\r\n");
      out.write("	  <div class=\"container\">\r\n");
      out.write("	    <h1>아이디가 일치하는 회원정보 조회 (AJAX)</h1>\r\n");
      out.write("	    <p class=\"lead\">\r\n");
      out.write("	    	검색할 아이디 : <input type=\"text\" id=\"searchId\">\r\n");
      out.write("	    	<button type=\"button\" id=\"idSearchBtn\">아이디 검색</button>\r\n");
      out.write("		</p>\r\n");
      out.write("		\r\n");
      out.write("		<table>\r\n");
      out.write("			<tbody id=\"idSearchResult\">\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td>이라는 회원이 존재하지 않습니다.</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th>아이디</th>\r\n");
      out.write("					<td>user01</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th>이름</th>\r\n");
      out.write("					<td>user01</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th>전화번호</th>\r\n");
      out.write("					<td>user01</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th>이메일</th>\r\n");
      out.write("					<td>user01</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th>주소</th>\r\n");
      out.write("					<td>user01</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("			</tbody>\r\n");
      out.write("		</table>\r\n");
      out.write("	  </div>\r\n");
      out.write("	</section>\r\n");
      out.write("	\r\n");
      out.write("	<!-- footer include -->\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\r\n");
      out.write("	\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
