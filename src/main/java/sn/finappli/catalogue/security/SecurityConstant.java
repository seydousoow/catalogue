package sn.finappli.catalogue.security;

final class SecurityConstant {
    static final String HEADER_NAME = "Authorization";
    static final String TOKEN_PREFIX = "Catalogue ";
    static final String SECRET = "5v8y/B?E(H+MbQeThWmZq4t7w9z$C&F)J@NcRfUjXn2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRfUjXn2r5u8x/A?D(G+KbPd" +
            "SgVkYp3s6v9y$B&E) H@McQfThWmZq4t7w!z%C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+";
    static final Long EXPIRATION = (long) (3 * 60 * 60 * 1000);
    static final String CLAIM_NAME = "authorities";
}
