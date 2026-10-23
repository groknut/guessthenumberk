# GuessTheNumberK

Игра «Угадай число» — задание по курсу «Основы мобильной разработки»
(«Игра "Угадай число"», `task_id 7225`).

Игрок загадывает число и сообщает приложению диапазон. Приложение задаёт
вопросы и угадывает число, деля диапазон пополам.

## Экраны (2 activity)

| Экран | Activity | Разметка |
|---|---|---|
| Задание диапазона | `MainActivity` | `res/layout/activity_main.xml` |
| Угадывание | `GameActivity` | `res/layout/activity_game.xml` |

## Алгоритм угадывания

Инвариант: загаданное число лежит в `[begin, end]`.

1. Пока в диапазоне больше двух чисел — вопрос «Ваше число больше `pivot`?»,
   где `pivot` — середина диапазона. Ответ «Да» → `begin = pivot + 1`,
   «Нет» → `end = pivot`. Диапазон уменьшается примерно вдвое.
2. Когда остаётся два соседних числа — вопрос «Ваше число — `end`?».
   Ответ «Да» → число `end`, «Нет» → число `begin`.
3. Если диапазон сузился до одного числа (`begin == end`) — угадывать нечего,
   сразу показываем результат.

Число вопросов не превышает ⌈log₂ n⌉: для 0…100 — максимум 7 вопросов
(проверено перебором всех чисел для ряда диапазонов).

## Стек

- Gradle 9.6.0 (wrapper), Android Gradle Plugin 9.4.1
- Kotlin — встроен в AGP 9 (плагин `org.jetbrains.kotlin.android`
  не подключается: иначе падает с `Cannot add extension with name 'kotlin'`)
- `compileSdk` 37, `targetSdk` 37, `minSdk` 24
- Java 11, `androidx.appcompat` + XML-разметка (без Compose)

## Сборка

Gradle 9 требует JDK 17+. В системе по умолчанию Java 8, поэтому указываем JDK
из Android Studio:

```bash
cd /home/groknut/Projects/isu/guessTheNumberK
JAVA_HOME=$HOME/.local/bin/android-studio/jbr ./gradlew assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`.

## Структура

```
app/src/main/AndroidManifest.xml
app/src/main/java/com/example/guessthenumberk/MainActivity.kt   # экран 1: диапазон
app/src/main/java/com/example/guessthenumberk/GameActivity.kt   # экран 2: угадывание
app/src/main/res/layout/activity_main.xml
app/src/main/res/layout/activity_game.xml
app/src/main/res/values/strings.xml
```

## Скриншоты

В этот раз не приложены. Чтобы снять их, нужен работающий эмулятор или
устройство; эмулятору требуется доступ к `/dev/kvm`
(`sudo chmod 666 /dev/kvm` либо `sudo gpasswd -a $USER kvm` + повторный вход):

```bash
android emulator create medium_phone
android emulator start medium_phone
android run --apks app/build/outputs/apk/debug/app-debug.apk
android screen capture -o screenshot-main.png   # экран 1
# пройти игру по шагам
android screen capture -o screenshot-game.png   # экран 2
```
