# Introduction

Thanks for contributing to our project! Please take a moment to read the following guidelines.
<br>
Following these guidelines helps streamline your contribution and our review process (there's also some tips and tricks
to help you breeze through your cross-platform modding spree). It also just keeps things running smoothly. We appreciate
your time and effort.

### Ways to Contribute

Not sure where to start? Here are a few ways you can help out:

* Reporting bugs
* Suggesting new features
* Writing or editing documentation (including typos)
* Refactoring code
* Reviewing pull requests
* Answering questions on issues
* Helping others in the community

### Ways NOT to Contribute

To keep the project maintainable, we have to turn down some contributions. Here are some things to avoid:

* Please don't use the issue tracker for personal support requests (use Discord).
* Please don't derail or troll issues. Keep the discussion on topic and respect the opinions of others.
* Please don't open issues or pull requests with untested code.
* Please don't open issues or pull requests with code that doesn't follow the code style guidelines (see below).
* Please don't open pull requests for new features without first getting our opinion on the feature (open an issue
  first).

# Ground Rules

* Be respectful and considerate of others
* Accept constructive criticism gracefully
* Focus on what is best for the community
* Show empathy towards other community members
* Be mindful of your language
* Be welcoming to newcomers and encourage diverse new contributors from all backgrounds
* Be transparent and get community feedback for major changes and enhancements

# Getting started

## How to report a bug

If you think you've found a security vulnerability, do NOT open an issue. Email `dylan@neuralnexus.dev` instead.

When filing an issue, make sure to answer the following questions:

Please note: You will not receive support if you are running Minecraft with offline mode without a proxy.

1. What version of TaterLib are you using? (do not say "latest")
2. What Minecraft version are you using?
3. What mod loader/server software are you using?
4. Reproduce the bug using the steps you took
5. What did you expect to happen?
6. What happened instead?
7. Include the full client/server/proxy logs e.g. `logs/latest.log`, `logs/debug.log`, and `crash-reports/*.txt` (upload
   them all if applicable). Use a paste site like https://mclo.gs (use (GitHub Gists)[https://gist.github.com] if the
   logs are too long). Do not paste them directly into the issue. If you
   trim the logs, you will not receive support.
8. Include your `taterlib.toml` file (omit sensitive information)
9. Include any other relevant information

## How to submit a contribution

Pick an issue and let us know you’re working on it. If you don’t see anything you’d like to work on, feel free to open a
new issue to discuss the changes you’d like to make.

1. Pick an issue to work on, create a new issue, or find some floating TODOs in the code.
2. Create your own fork of the code
3. Do the changes in your fork
4. Testing your changes
    1. If your feature adds to or modifies features in TaterLib's API, please create a corresponding JUnit test if
       possible (some things can only be tested in a game environment)
    2. If your feature adds an event/platform-reliant feature, or mod interaction:
        * Pick two mod loaders that apply (e.g. don't test on Bukkit-based software if your addition is client-side)
        * Test your changes on each Major minecraft version that apply to your changes (e.g. don't test below 1.15 if
          your code only interacts with bees)
5. If you're satisfied with your changes, submit a pull request
    * Be sure you have followed the code style for the project

### Code style

To keep things simple, we use [google-java-format](https://plugins.jetbrains.com/plugin/8527-google-java-format) for
IntelliJ IDEA to format our code. The only deviation from the default settings is that we use the ASOP style format. You
can set this just by going to `Settings > google-java-format Settings > Code style` and selecting `AOSP` from the
dropdown.

I usually use the following settings applied under `Settings > Tools > Actions on Save`:

- Reformat code
- Optimize imports
- Rearage code
- Run code cleanup

### Tips, Tricks, and Other Notes

Due to the size, nature, and complexity of the project's build layout, you'll probably want to comment out any
unnecessary `include()` entries inside the top level `settings.gradle` file. Setting up this project from an empty
gradle cache can take up to an hour or longer in some cases, given that many modding build tools also need to decompile,
remap, and validate the vanilla source. Please note: exclude the `settings.gradle` from any commits, or at the very
least, un-comment the other entries once you're finished.

PRs are not considered finished until every platform/version has the feature implemented (excluding unsupported events).
For methods that aren't supported by and older version, please add the `{toBeNamed}RuntimeException`.

Please remember: You are not expected to know everything about every modding platform. If you're not sure about
something, feel free to ask. Or if you're feeling a bit overwhelmed, ask for help, you aren't alone while
working on PRs.

It sounds like a lot, but most of the code is repetitive, and you'll get the hang of it after a bit. Even if you just
start a PR, someone else can pick it up and help finish it.

#### Access Transformers/Wideners

They're great, but they're not always the best solution. Limit their usage when possible, mainly because it'll force a
re-decompile/resync with some build tools.

### Commit messages

Please follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification when writing your
commit messages.

### Testing

If your feature adds to or modifies features in TaterLib's API, please create a corresponding JUnit test if possible.

As mentioned above, please test your changes on each major version that applies to your changes, and on two mod loaders
that apply to your changes. If you're interfacing with a specific mod or modloader API, test on the latest version of
that mod/modloader.

Someday I hope to set up a CI system with a custom mod that depends on TaterLib that we could use for makeshift unit
tests, but for now, we'll have to rely on the tried and true method of manual testing. I guess that's the downside of
supporting all modding platforms.

# Ramblings

### GameTest Framework

I know the testing framework is available for 1.17+, but there's no good way to implement cross-platform tests (at least
that I can see at this time, might see if I can add something to the vanilla projects). Though we'll still need
something for older versions.

### Other Potential Modding Platforms

If you're feeling really ambitious, you could try to add support for other modding platforms, I've been considering the
following:

* [Nukkit](https://github.com/CloudburstMC/Nukkit)
* RiftMC
* LiteLoader
* Meddle
* Risugami
* LitLaunch
* RealML
* [Minestorm](https://github.com/Minestom/Minestom)
* [Cursed Fabric](https://minecraft-cursed-legacy.github.io/)
