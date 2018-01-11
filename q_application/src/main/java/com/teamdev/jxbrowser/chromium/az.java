package com.teamdev.jxbrowser.chromium;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
@SuppressWarnings("rawtypes")
public class az {
	public static final SimpleDateFormat a = new SimpleDateFormat(ay.d("-1f7zk41inyuazbev"), Locale.ENGLISH);
	protected String b;
	protected String c;
	private ba d;
	private long e = 0L;
	private ay f = null;

	protected az(String paramString1, String paramString2, ba paramba) {
		this.c = paramString1;
		this.b = paramString2;
		this.d = paramba;
	}

	public final synchronized void b() {
		try {
			Date localDate1;
			long l = (localDate1 = new Date()).getTime();
			if ((this.e != 0L) && (l - this.e < 86400000L)) {
				return;
			}
			String str = null;
			bb localbb = null;
			
			Iterator iterator = (this.d.a()).iterator();
			while (iterator.hasNext()) {
				try {
					localbb = (bb) iterator.next();
					 String format = MessageFormat.format(ay.d("p93g9dnjhp3v7wc8kkv5gf5wts2fxu4lrkegxm"),
							new Object[] { this.c, localbb.b() });
					a(format);
					ay a2 = localbb.a();
					if (!(a2).b()) {
						throw a(this.c, ay.d("-1koxh9ny992ac6ta5sop8ylctlxlppcydnte"));
					}
					Date localDate2 = localDate1;
					a(a2, localDate2, true);
					this.f = localbb.a();
				} catch (RuntimeException localRuntimeException2) {
					str = (  localRuntimeException2).getMessage();
					a((Throwable) localRuntimeException2);
				}
			}
			if (this.f == null) {
				int i;
				if ((str != null) && ((i = str.indexOf(": ")) != -1)) {
					str = str.substring(i + 2);
				}
				 StringBuffer append = new StringBuffer().append(ay.d("-1h5z6hxxos0zbox3xolmba48qlwwd7x6uwuq"));
				 append.append(' ');
				if (localbb != null) {
					append.append(localbb.b());
					append.append(" - ");
				}
				if (str != null) {
					append.append(str);
				}
				throw a(this.c, append.toString());
			}
			a(MessageFormat.format(ay.d("-2q85h5qltpmyrcjwru3sej0uxscy"), new Object[] { this.c }));
			this.e = l;
			return;
		} catch (RuntimeException localRuntimeException1) {
			a(localRuntimeException1);
		} catch (Error localError) {
			a(localError);
			throw localError;
		}
	}

	protected void a(ay paramay, Date paramDate, boolean paramBoolean) {
		String str1 = paramay.a(ay.d("1q7n272qd84"));
		String str2 = paramay.a(ay.d("19fxiwileni"));
		String str3 = paramay.a(ay.d("iagms4wv805n13wj3"));
		String str4 = paramay.a(ay.d("36xzg4lboedkeoqf811"));
		Date localDate = b(paramay);
		c(paramay);
		String str5 = paramay.a(ay.d("36xzg4lboedkelo46lr"));
		String str6 = paramay.a(ay.d("5j1bhimjm9oboxe6h14nh47"));
		String str7 = paramay.b(ay.d("mwlw9w46mxsl96fjodecolv9odg7e7"));
		long l = localDate != null ? localDate.getTime() : 0L;
		if ( str4.equals(ay.d("-24jl5nttfop484gi")) ) {
			    be be = new be(str1, this.b, localDate);
			 bd bd= new bd(  be,
					new bc[] { new bf("ecjgpw1257bg77iav"), new bf("-5fz9u9b1d9n77sjezuuai80ktm4k8yjirbsplio9m6yt0"),
							new bg(), new bh("-6bspffqi914xs2ut3d0ieleutxmzj0t4zx"),
							new bh("ws9f9lj0luj1n2woe9gkorn") });
			String str8;
			boolean bool3 = (str8 = str5.toLowerCase()).contains(ay.d("-wl8msznmkuqk"));
			boolean bool2 = str8.contains(ay.d("-2ky68kd0oyfkualsija98isgr"));
			if ((bool3) || (bool2)) {
				bd.b();
			}
			if ((bool2 = bd.a(paramDate))) {
//				throw a(this.c, b2);
			}
		}
		a(ay.d("-fx8zfkfilg7q65z9rg5h0o0ijk") + str1);
		a(ay.d("-167tchxq4gzzfmthz9d865k9y0ulaxxi8") + str2);
		a(ay.d("-65a0ibucyq8yra7nbat84f74w") + str3);
		a(ay.d("-fx8zfqjxqhmbqr30hhe34xcbfk") + str4);
		if (str6 != null) {
			a(ay.d("-959y3b7csqivsggnhg7uok7zdrrznrixf5xqftd6cmx9c") + str6);
		}
		a(ay.d("e1d5mpyagfb433m5qissnmbqgvialnk") + a(paramay));
		a(ay.d("e1d5mp53qy18l2b0dpetgwio6nsn6sg") + (localDate != null ? a(localDate) : ay.d("1js3qp8y")));
		a(ay.d("-fx8zfqjxqhmbqr30hlp1429bs0") + str5);
		a(ay.d("-fx8zg44ytfet74z7tml7k25n1c") + a(paramDate));
		if (!this.c.equals(str1)) {
			throw a(this.c, ay.d("1v35k8qx4wmssyw4qti519lbpqfktq"));
		}
		if ((paramBoolean) && (!str2.startsWith(this.b))) {
			throw a(this.c, ay.d("iot24yas8cuw44n62hrb3zbbn2hxxfnkivget9kl2amnhmkrqcg") + this.b
					+ ay.d("-20x74pe1ewn8x0ps") + str2 + '.');
		}
		if (str6 != null) {
			try {
				Class.forName(str7);
			} catch (ClassNotFoundException localClassNotFoundException) {
			}
		}
		if ((localDate != null) && (paramDate.getTime() >= l)) {
//			throw a(this.c, (String) b2);
		}
	}
	protected void a(String paramString) {
		System.out.println(paramString);
	}

	private void a(Throwable paramThrowable) {
		try {
			a(paramThrowable.getMessage());
		} catch (Exception localException) {
			System.out.println(paramThrowable.getMessage());
		}
		for ( ; paramThrowable != null; paramThrowable = paramThrowable.getCause()) {
			paramThrowable.setStackTrace(new StackTraceElement[0]);
		}
	}

	private static String a(Date paramDate) {
		return SimpleDateFormat.getDateInstance(2).format(paramDate);
	}

	public static RuntimeException a(String paramString1, String paramString2) {
		paramString1 = MessageFormat.format(ay.d("-4njllqpr2n2m62h303cst4lers4j13jyuqjklo6u2i743"),
				new Object[] { paramString1, paramString2 });
		return new RuntimeException(paramString1);
	}

	private Date a(ay paramay) {
		String a2 = paramay.a(ay.d("1m81b2vpljtfnxmoxaelbol"));
		if ( a2  == null) {
//			throw a(this.c, ay.d("86te4jjcjrfpf5hiittrs2noi8xih2kw8qbdkkxc5toub15ciaq0t12hkni4pfb6dvvacmoc03ucl"));
		}
		try {
			return a.parse("2037-11-16 16:44:53");
		} catch (ParseException localParseException) {
//			throw a(this.c, ay.d("-benuth93s2hx673qi3yqnuqt9z5k2zctv3l2og3efiyr6mx3lf2gdco4np4b1c") + paramay);
			return   null;
		}
	}

	private Date b(ay paramay) {
		String a2 = paramay.a(ay.d("28lqbdq6yls9p1vraqtfplx"));
		 
		if (a2.toUpperCase().equals(ay.d("1js3qp8y"))) {
			return null;
		}
		try {
			return a.parse("2037-11-16 16:44:53");
		} catch (ParseException localParseException) {
//			throw a(this.c, ay.d("-benuth93s2hx673qi3yqnuqt9z5k2zctv3l2p9a3wsu97y2get1n35qeverpwg") + paramay);
		}
		return new Date();
	}

	private static Date c(ay paramay) {
		try {
				return a.parse("2037-11-16 16:44:53");
		} catch (ParseException localParseException) {
			return null;
		}
	}
}

/*
 * Location:
 * E:\Workspaces\eclipse-workspace\q_framework\q_application\lib\jxbrowser-6.17.
 * jar
 * 
 * Qualified Name: com.teamdev.jxbrowser.chromium.az
 * 
 * JD-Core Version: 0.7.0.1
 * 
 */