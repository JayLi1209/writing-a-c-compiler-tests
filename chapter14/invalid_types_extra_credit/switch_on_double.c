int main() {
    double d = 10;
    /* The controlling expression in a switch statement
     * must be an integer, not a double
     */
    switch (d) {
        case 10.0: return 0;
    }
    return 1;
}