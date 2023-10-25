Tambahkan library berikut pada build.gradle(Module: app).
implementation "io.reactivex.rxjava2:rxjava:2.2.19"
implementation "com.jakewharton.rxbinding2:rxbinding:2.0.0"

RxBinding adalah library yang digunakan untuk membuat Observable dari komponen User Interface seperti EditText, SearchView dan Radio Group. 

Hapus beberapa kode yang akan kita ganti. Mulai dari flag yaitu variabel emailValid, passwordValid, passwordConfirmationValid. Kemudian addTextChangedListener pada masing-masing EditText. Dan terakhir hapus juga fungsi-fungsi untuk validasi seperti validateEmail, validatePassword, validatePasswordConfirmation, dan validateButton. Sehingga kode pada MainActivity

Tambahkan data stream dari inputan Email dilanjutkan dengan subscribe ke stream tersebut.

```
 // TODO : Data stream emailStream, passwordStream (subscribe ke stream)
        val emailStream = RxTextView.textChanges(binding.edEmail)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            showEmailExistAlert(it)
        }

        val passwordStream = RxTextView.textChanges(binding.edPassword)
            .skipInitialValue()
            .map { password ->
                password.length < 6
            }
        passwordStream.subscribe {
            showPasswordMinimalAlert(it)
        }
```

Nah, untuk yang confirmation password di sini akan sedikit berbeda. Hal ini karena Anda mengecek pada dua inputan sekaligus, yaitu pada ed_password dan ed_confirm_password. Karena itulah kita perlu menggabungkan dua data tersebut dengan operator merge seperti berikut ini:

```
val passwordConfirmationStream = Observable.merge(
        RxTextView.textChanges(binding.edPassword)
            .map { password ->
                password.toString() != binding.edConfirmPassword.text.toString()
            },
        RxTextView.textChanges(binding.edConfirmPassword)
            .map { confirmPassword ->
                confirmPassword.toString() != binding.edPassword.text.toString()
            }
    )
    passwordConfirmationStream.subscribe {
        showPasswordConfirmationAlert(it)
    }
```

Terakhir, kita perlu membaca ketiga data stream tersebut untuk menentukan apakah tombol diaktifkan atau tidak. Maka diperlukan operator combineLatest seperti berikut:
```
    val invalidFieldsStream = Observable.combineLatest(
        emailStream,
        passwordStream,
        passwordConfirmationStream,
        Function3 { emailInvalid: Boolean, passwordInvalid: Boolean, passwordConfirmationInvalid: Boolean ->
            !emailInvalid && !passwordInvalid && !passwordConfirmationInvalid
        })
    invalidFieldsStream.subscribe { isValid ->
        if (isValid) {
            binding.btnRegister.isEnabled = true
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
        } else {
            binding.btnRegister.isEnabled = false
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        }
    }
    
    val passwordConfirmationStream = Observable.merge(
        RxTextView.textChanges(binding.edPassword)
            .map { password ->
                password.toString() != binding.edConfirmPassword.text.toString()
            },
        RxTextView.textChanges(binding.edConfirmPassword)
            .map { confirmPassword ->
                confirmPassword.toString() != binding.edPassword.text.toString()
            }
    )
    passwordConfirmationStream.subscribe {
        showPasswordConfirmationAlert(it)
    }
```


Terakhir, kita perlu membaca ketiga data stream tersebut untuk menentukan apakah tombol diaktifkan atau tidak. Maka diperlukan operator combineLatest seperti berikut:
```
    val invalidFieldsStream = Observable.combineLatest(
        emailStream,
        passwordStream,
        passwordConfirmationStream,
        Function3 { emailInvalid: Boolean, passwordInvalid: Boolean, passwordConfirmationInvalid: Boolean ->
            !emailInvalid && !passwordInvalid && !passwordConfirmationInvalid
        })
    invalidFieldsStream.subscribe { isValid ->
        if (isValid) {
            binding.btnRegister.isEnabled = true
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
        } else {
            binding.btnRegister.isEnabled = false
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        }
    }
```
