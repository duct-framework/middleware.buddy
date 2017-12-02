# Duct middleware.buddy

A [Duct][] library that provides keys for (some) [Buddy][] middleware.

[duct]:  https://github.com/duct-framework/duct
[buddy]: https://github.com/funcool/buddy

## Installation

To install, add the following to your project `:dependencies`:

    [duct/middleware.buddy "0.1.0-SNAPSHOT"]

## Usage

This library is currently *very* bare bones. It currently supports only
basic authentication.

The `:duct.middleware.buddy/authentication` key wraps the
[buddy.auth.middleware/wrap-authentication][wrap-auth] function. The
authentication backend is denoted by a keyword on the `:backend` key:

```edn
{:duct.middleware.buddy/authentication
 {:backend :basic
  :realm   "Example"
  :authfn  #ig/ref :example.auth/basic-auth}

 :example.auth/basic-auth {}}
```

[wrap-auth]: https://funcool.github.io/buddy-auth/latest/api/buddy.auth.middleware.html#var-wrap-authentication

## License

Copyright © 2017 James Reeves

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
