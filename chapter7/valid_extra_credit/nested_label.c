int main() {
    goto labelB;

    labelA:
        labelB:
            return 5;
    return 0;
}