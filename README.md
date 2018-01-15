# Duct middleware.buddy

[![Build Status](https://travis-ci.org/duct-framework/middleware.buddy.svg?branch=master)](https://travis-ci.org/duct-framework/middleware.buddy)

A [Duct][] library that provides keys for (some) [Buddy][] middleware.

[duct]:  https://github.com/duct-framework/duct
[buddy]: https://github.com/funcool/buddy

## Installation

To install, add the following to your project `:dependencies`:

    [duct/middleware.buddy "0.1.0"]

## Usage

This library currently only supports a single [Integrant][] key:
`:duct.middleware.buddy/authentication`. This keyword wraps the
[buddy.auth.middleware/wrap-authentication][wrap-auth] function. The
authentication backend is denoted by a keyword on the `:backend` key:

```edn
{:duct.middleware.buddy/authentication
 {:backend :basic
  :realm   "Example"
  :authfn  #ig/ref :example.auth/basic-auth}

 :example.auth/basic-auth {}}
```

Five backends are supported:

* `:basic`
* `:session`
* `:token`
* `:jws`
* `:jwe`

These are covered in more detail in the [buddy-auth
documentation][buddy-auth].

[integrant]:  https://github.com/weavejester/integrant
[wrap-auth]:  https://funcool.github.io/buddy-auth/latest/api/buddy.auth.middleware.html#var-wrap-authentication
[buddy-auth]: https://funcool.github.io/buddy-auth/latest/

## License

Copyright © 2017 James Reeves

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
