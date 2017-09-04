

package developertrack.testphoto;

import android.content.SharedPreferences;
import android.webkit.CookieManager;

public class InstagramManager {

	private static InstagramManager manager;
	private String access_token;

	SharedPreferences settings;
	SharedPreferences.Editor editor;

	private InstagramManager() {

	}

	public static InstagramManager getInstance() {

		if (manager == null) {

			synchronized (InstagramManager.class) {

				if (manager == null) {

					manager = new InstagramManager();
				}
			}
		}
		return manager;
	}

	public void setAccessToken(String token) {

		this.access_token = token;
	}

	public String getAccessToken() {
		return this.access_token;
	}

	public String getInstagramCookies() {

		CookieManager cookieMgr = CookieManager.getInstance();
		String cookies = null;
		if (cookieMgr.hasCookies()) {
			cookies = cookieMgr.getCookie("https://instagram.com/");
		}

		return cookies;
	}

	public void deleteInstagramCookies() {

		CookieManager cookieMgr = CookieManager.getInstance();
		if (cookieMgr.hasCookies()) {

			cookieMgr.removeAllCookie();
		}
	}

	public String urlStringForAuthenticationRequest() {

		String apiBaseUrl = Constants.BASE_URL;
		String authenticate = Constants.AUTHENTICATE;
		String clientId = Constants.CLIENT_ID;
		String redirectUri = Constants.REDIRECT_URI;

		String strUrl = apiBaseUrl + authenticate + "client_id=" + clientId
				+ "&redirect_uri=" + redirectUri;
		return strUrl;
	}

	private String urlStringForUserInfoRequest() {

		String apiBaseUrl = Constants.API_BASE_URL;
		String selfInfo = Constants.SELF_INFO;
		String clientId = Constants.CLIENT_ID;
		String redirectUri = Constants.REDIRECT_URI;

		String strUrl = apiBaseUrl + selfInfo + "access_token="
				+ this.access_token;
		return strUrl;
	}

	private String urlStringForRecentMediaRequest() {

		String apiBaseUrl = Constants.API_BASE_URL;
		String selfMedia = Constants.SELF_MEDIA;

		String strUrl = apiBaseUrl + selfMedia + "access_token="
				+ this.access_token;
		return strUrl;
	}

}
