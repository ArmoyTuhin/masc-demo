# MASC Demo: Cryptographic Misuse Detection Test Project

A minimal Java Maven project created to test and evaluate cryptographic API misuse detection tools (crypto-detectors) as described in the paper "Why Crypto-detectors Fail: A Systematic Evaluation of Cryptographic Misuse Detection Techniques".

The paper introduces the MASC framework (Mutation-based Analysis of Static Crypto-misuse detection techniques), which uses mutation testing to systematically evaluate crypto-detectors. This demo project contains intentional cryptographic misuses for testing various crypto-detection tools.

## Purpose

This repository was created to enable AI-assisted security improvements and comprehensive evaluation of crypto-detectors. The goals are:

1. **AI-Assisted Security**: Use AI to identify and fix cryptographic misuses, making the code secure
2. **Mutation Case Expansion**: Add more mutation cases to test crypto-detectors more thoroughly
3. **Failure Analysis**: Identify where and why crypto-detectors fail to detect misuses

This project serves as a testbed for evaluating crypto-detectors such as CogniCrypt, CryptoLint, CryptoGuard, and SpotBugs Find Security Bugs plugin.

## Project Structure

```
masc-demo/
├── src/main/java/demo/
│   ├── CryptoConfig.java      # Crypto configuration with ECB mode misuse
│   ├── Encryptor.java          # Encryption utilities with various misuses
│   └── SecurityProfile.java    # Security profile with hash algorithm usage
└── pom.xml                     # Maven configuration
```

## Cryptographic Misuses

This project intentionally contains cryptographic misuses that crypto-detectors should identify:

1. **ECB Mode Usage** - Using Electronic Code Book mode for encryption (insecure)
2. **Weak Hash Algorithms** - Potential use of MD5 or SHA-1 (cryptographically broken)
3. **Key Management Issues** - Methods that allow hardcoded or weak keys

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+

### Build

```bash
git clone https://github.com/ArmoyTuhin/masc-demo.git
cd masc-demo
mvn clean compile
```

## Testing with Crypto-Detectors

### CogniCrypt (Eclipse Plugin)

1. Install Eclipse IDE and CogniCrypt plugin from Eclipse Marketplace
2. Import this project: File → Import → Existing Maven Projects
3. Run analysis: Right-click project → CogniCrypt → Analyze Project

### CryptoLint

1. Download CryptoLint JAR from GitHub
2. Run: `java -jar cryptolint.jar -p src/main/java`

### Expected Findings

```
[Warning] Encryptor.java: Cipher mode ECB is insecure.
[Warning] CryptoConfig.java: Using ECB mode is insecure.
[Warning] SecurityProfile.java: Weak hash algorithm MD5 (if configured).
```

## MASC Framework Context

The MASC framework evaluates crypto-detectors using:
- Taxonomy of 105 crypto-API misuse cases
- 12 usage-based mutation operators
- 3 mutation scopes for threat scenario emulation

This demo contains a subset focusing on:
- Compromising Secrecy of Cipher Text (ECB mode)
- Compromised Checksums (weak hash algorithms)
- Secret Key Misuses (hardcoded/weak keys)

## Disclaimer

This project contains intentional security vulnerabilities for testing purposes. Do not use this code in production environments.
