# Contributing to AgreementKit

Thank you for your interest in contributing to AgreementKit! We welcome contributions from the community and appreciate your help in making this library better.

## 🤝 How to Contribute

### Reporting Issues

Before creating an issue, please:

1. **Search existing issues** to avoid duplicates
2. **Check the FAQ** for common solutions
3. **Use the issue template** with all required information

#### Bug Reports

When reporting a bug, please include:

- **Library version** you're using
- **Android version** and device information
- **Steps to reproduce** the issue
- **Expected behavior** vs **actual behavior**
- **Logs and screenshots** if applicable
- **Code snippets** that demonstrate the issue

#### Feature Requests

For feature requests, please include:

- **Clear description** of the feature
- **Use case** and why it's needed
- **Proposed implementation** if you have ideas
- **Alternatives** you've considered

### Code Contributions

#### Getting Started

1. **Fork the repository**
2. **Clone your fork**:
   ```bash
   git clone https://github.com/your-username/AgreementKit.git
   cd AgreementKit
   ```
3. **Create a feature branch**:
   ```bash
   git checkout -b feature/your-feature-name
   ```

#### Development Setup

1. **Open in Android Studio**
2. **Sync the project** with Gradle
3. **Run tests** to ensure everything works
4. **Make your changes**

#### Code Style

We follow these coding standards:

- **Kotlin**: Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- **Compose**: Follow [Compose Guidelines](https://developer.android.com/jetpack/compose/guidelines)
- **Material Design**: Follow [Material Design 3](https://m3.material.io/) guidelines
- **Architecture**: Follow MVVM pattern with clean architecture principles

#### Code Formatting

- Use **4 spaces** for indentation
- **Maximum line length**: 120 characters
- **Use meaningful variable names**
- **Add comments** for complex logic
- **Follow existing code style** in the project

#### Testing

All contributions must include tests:

- **Unit tests** for business logic
- **UI tests** for Compose components
- **Integration tests** for API interactions
- **Test coverage** should be maintained or improved

#### Documentation

Update documentation for any changes:

- **README.md** for new features
- **API Reference** for new APIs
- **Usage Examples** for new use cases
- **Inline comments** for complex code

### Pull Request Process

#### Before Submitting

1. **Test your changes** thoroughly
2. **Run all existing tests** to ensure nothing breaks
3. **Update documentation** as needed
4. **Follow the PR template**

#### PR Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests pass
- [ ] UI tests pass
- [ ] Manual testing completed

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] Tests added/updated
```

#### Review Process

1. **Automated checks** must pass
2. **Code review** by maintainers
3. **Testing** by the team
4. **Approval** from at least one maintainer

## 🏗️ Project Structure

```
agreementkit/
├── src/main/java/com/sepanta/controlkit/agreementkit/
│   ├── AgreementKit.kt                 # Main library class
│   ├── service/                        # API and network layer
│   │   ├── AgreementApi.kt
│   │   ├── ApiService.kt
│   │   └── model/
│   ├── view/                          # UI and ViewModel layer
│   │   ├── ui/
│   │   ├── viewmodel/
│   │   └── config/
│   ├── theme/                         # Theme and styling
│   └── utils/                         # Utility classes
├── src/test/                          # Unit tests
├── src/androidTest/                   # Integration tests
└── docs/                             # Documentation
```

## 🧪 Testing Guidelines

### Unit Tests

```kotlin
class AgreementKitTest {
    @Test
    fun `test agreement configuration`() {
        // Test your code here
    }
}
```

### UI Tests

```kotlin
@Test
fun testAgreementDialog() {
    composeTestRule.setContent {
        // Your UI test here
    }
}
```

### Test Coverage

- **Minimum coverage**: 80%
- **Critical paths**: 100% coverage
- **New features**: Must include tests

## 📝 Documentation Guidelines

### Code Comments

```kotlin
/**
 * Displays the agreement dialog with the specified configuration.
 * 
 * @param config The service configuration
 * @param onDismiss Callback when dialog is dismissed
 * @param onState Callback for state changes
 * @return AgreementKit instance
 */
@Composable
fun agreementKitHost(
    config: AgreementServiceConfig,
    onDismiss: (() -> Unit)? = null,
    onState: ((AgreementState) -> Unit)? = null
): AgreementKit
```

### README Updates

- **Clear examples** with code snippets
- **Step-by-step instructions**
- **Screenshots** for UI changes
- **Links** to relevant documentation

## 🚀 Release Process

### Versioning

We follow [Semantic Versioning](https://semver.org/):

- **MAJOR**: Breaking changes
- **MINOR**: New features (backward compatible)
- **PATCH**: Bug fixes (backward compatible)

### Release Checklist

- [ ] All tests pass
- [ ] Documentation updated
- [ ] Changelog updated
- [ ] Version bumped
- [ ] Tag created
- [ ] Release notes written

## 🐛 Bug Fixes

### Priority Levels

1. **Critical**: Security issues, crashes
2. **High**: Major functionality broken
3. **Medium**: Minor issues, improvements
4. **Low**: Nice-to-have features

### Fix Process

1. **Reproduce the bug**
2. **Write a failing test**
3. **Fix the issue**
4. **Ensure test passes**
5. **Update documentation**

## ✨ Feature Development

### Feature Request Process

1. **Create an issue** with detailed description
2. **Discuss** with maintainers
3. **Get approval** before starting
4. **Implement** with tests
5. **Submit PR** for review

### Feature Guidelines

- **Backward compatibility** unless breaking change is necessary
- **Comprehensive testing** required
- **Documentation** must be updated
- **Performance** impact considered

## 🔒 Security

### Security Issues

For security-related issues:

1. **DO NOT** create public issues
2. **Email** security@yourcompany.com
3. **Include** detailed information
4. **Wait** for response before disclosure

### Security Guidelines

- **Never commit** sensitive information
- **Use secure** coding practices
- **Validate** all inputs
- **Follow** OWASP guidelines

## 📞 Getting Help

### Communication Channels

- **GitHub Issues**: Bug reports and feature requests
- **GitHub Discussions**: General questions and ideas
- **Email**: support@yourcompany.com
- **Discord**: [Join our community](https://discord.gg/your-invite)

### Response Times

- **Critical issues**: 24 hours
- **Regular issues**: 3-5 business days
- **Feature requests**: 1-2 weeks
- **General questions**: 1-3 business days

## 🏆 Recognition

### Contributors

We recognize contributors in:

- **README.md** contributors section
- **Release notes** for significant contributions
- **GitHub** contributor statistics
- **Special thanks** in documentation

### Types of Contributions

- **Code**: Bug fixes, features, improvements
- **Documentation**: Guides, examples, API docs
- **Testing**: Test cases, bug reports
- **Community**: Help others, answer questions

## 📋 Code of Conduct

### Our Pledge

We are committed to providing a welcoming and inclusive environment for all contributors.

### Expected Behavior

- **Be respectful** and inclusive
- **Be constructive** in feedback
- **Be patient** with others
- **Be collaborative** and helpful

### Unacceptable Behavior

- **Harassment** or discrimination
- **Inappropriate** language or behavior
- **Spam** or off-topic discussions
- **Violation** of others' privacy

## 📄 License

By contributing, you agree that your contributions will be licensed under the same license as the project (MIT License).

## 🙏 Thank You

Thank you for contributing to AgreementKit! Your efforts help make this library better for everyone in the Android community.

---

**Questions?** Feel free to [open an issue](https://github.com/YourUsername/AgreementKit/issues) or [start a discussion](https://github.com/YourUsername/AgreementKit/discussions).
