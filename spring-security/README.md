# spring-security

## Authentication

Interface that represents an authentication attempt. Contains information about the principal trying to authenticate, e.g. username and password. Concrete implementations exist for various type of authentication mechanisms. The success will be decided by the `AuthenticationManager`.

A common type of `Authentication` is the `UsernamePasswordAuthenticationToken`, which is created by login via an HTML form or HTTP Basic Authentication.

## AuthenticationManager

Decides whether to grant authentication for an `Authentication` object by delegating to one or more `AuthenticationProvider`.

## AuthenticationProvider

Decides whether to grant authentication for an `Authentication` object. Targeted to a specific type of `AuthenticationToken`.

One particular example is the `DaoAuthenticationProvider` that accepts a `UsernamePasswordAuthenticationToken` and tries to match the credentials against a `UserDetailsService`.

## SecurityContext

Holds the `Authentication` object for the current thread, e.g. in the context of a HTTP request.

## SecurityFilterChain

Key component of Spring Security that contains a list of servlet filters which are executed on a HTTP request in a certain order.

One type of filters are _authenticaton filters_, which are attempting to extract an `Authentication` from the HTTP request. Notable examples are `UsernamePasswordAuthenticationFilter` or `BasicAuthenticationFilter` for the authentication type of `UsernamePasswordAuthenticationToken`.

If none of the authentication filters managed to create an `Authentication` object, the `AnonymousAuthenticationFilter` will create an `AnonymousAuthenticationToken`.

## UserDetails

An implementing object of this interface represents a user, contains more information about the authenticated principal and also its authorities. This object is usually contained in the `Authentication` object. Subclasses can extend it to add more information, as is the case for `LdapUserDetails`.

## UserDetailsManager

An implementation of `UserDetailsService` that also allows modifying the user entries, whereas its superclass is read-only. 

## UserDetailsService

Component that maps a username to a `UserDetails` object.
