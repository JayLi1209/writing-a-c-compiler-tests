int main() {

    /* Make sure subnormal numbers are not rounded to zero */
    double subnormal = 2.5e-320;

    // subnormal is non-zero, so !subnormal should be zero
    return !subnormal;
}