package org.frank.bogle.filter;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.servlet.account.AccountResolver;
import com.stormpath.sdk.servlet.filter.HttpFilter;
import com.stormpath.spring.security.provider.StormpathAuthenticationProvider;
import com.stormpath.spring.security.token.ProviderAuthenticationToken;
import org.apache.oltu.oauth2.rs.extractor.BearerHeaderTokenExtractor;
import org.apache.oltu.oauth2.rs.extractor.TokenExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by frankbogle on 22/12/2016.
 */
public class PreAuthenticationFilter extends HttpFilter implements InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(PreAuthenticationFilter.class);

    private StormpathAuthenticationProvider stormpathAuthenticationProvider;
    private boolean stateless = false;
    private TokenExtractor tokenExtractor = new BearerHeaderTokenExtractor();

    /**
     * Autowire by Constructor
     * @param stormpathAuthenticationProvider
     */

    @Autowired
    public PreAuthenticationFilter(StormpathAuthenticationProvider stormpathAuthenticationProvider) {
        this.stormpathAuthenticationProvider = stormpathAuthenticationProvider;
    }

    public void afterPropertiesSet() {
        /**
         * todo method
         */
    }

    public void destroy() {
        /**
         * todo method
         */
    }

    @Override
    public void filter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        final boolean debug = logger.isDebugEnabled();

        String accessToken = tokenExtractor.getAccessToken(request);
        logger.info("PreAuthenticationFilter Access Token :::: " + accessToken);

        if (accessToken == null) {
            if (stateless && isAuthenticated()) {
                if (debug) {
                    logger.debug("PreAuthenticationFilter :::: Clearing Security Context");
                }

                SecurityContextHolder.clearContext();
            }
            if (debug) {
                logger.debug("PreAuthneticationFilter No Token in request, will continue chain");
            }
        } else {

            logger.info("PreAuthenticationFilter :::: Token null, creating new SecurityContext");
            Account account = AccountResolver.INSTANCE.getAccount(request);

            if (account != null) {

                Authentication authentication = new ProviderAuthenticationToken(account);
                authentication = stormpathAuthenticationProvider.authenticate(authentication);
                SecurityContextHolder.clearContext();
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        chain.doFilter(request, response);

    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return true;
    }

    public static Logger getLogger() {
        return logger;
    }

    public StormpathAuthenticationProvider getStormpathAuthenticationProvider() {
        return stormpathAuthenticationProvider;
    }

    public void setStormpathAuthenticationProvider(StormpathAuthenticationProvider stormpathAuthenticationProvider) {
        this.stormpathAuthenticationProvider = stormpathAuthenticationProvider;
    }

    public boolean isStateless() {
        return stateless;
    }

    public void setStateless(boolean stateless) {
        this.stateless = stateless;
    }

    public TokenExtractor getTokenExtractor() {
        return tokenExtractor;
    }

    public void setTokenExtractor(TokenExtractor tokenExtractor) {
        this.tokenExtractor = tokenExtractor;
    }
}
