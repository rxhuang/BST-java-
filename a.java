#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define BUFSIZE 100
#define TRUE 1

//struct tree_node can be reference simply as Node
typedef struct tree_node Node;

//struct definition of Node
struct tree_node {
    char * value;

    //TODO: complete struct tree_node definition
    Node * lChild;
    Node * rChild;
};

struct tree_node * newNode(char * input)
{
    Node * newNode;
    newNode = (Node *) malloc(sizeof(Node);
    newNode -> value = input;
    newNode -> lChild = Null;
    newNode -> rChild = Null;
    return newNode;
}

//TODO: what other variables might you need?
struct Node * root = NULL;

/*-----------------------------------------------------------------------------
 * Function name:   print
 * Input:           pointer to a struct of type Node 
 * Output:          none
 * Result:          the string value of node is printed
 * Notes:           DO NOT EDIT THIS FUNCTION
 ----------------------------------------------------------------------------*/
void printNode(Node * node) {
    printf("%s\n", node->value);
}

/*-----------------------------------------------------------------------------
 * Function name:   compare
 * Input:           two char pointers 
 * Output:          0  if both strings are equal
 *                  <0 the first character that does not match has a 
                        lower value in ptr1 than in ptr2
                    >0 the first character that does not match has a 
                        greater value in ptr1 than in ptr2
 * Notes:           USE THIS TO COMPARE STRINGS WHEN INSERTING/FINDING
 ----------------------------------------------------------------------------*/
int compare(char *a, char *b) {

    //TODO: your code goes here
    for(;*a==*b;a++.b++)
    {
        if(*a == '\0')
        {
            return 0;
        }
        if(*a!=*b)
        {
            return(*a-*b);
        }
}

/*-----------------------------------------------------------------------------
 * Function name:   insert
 * Input:           char pointer
 * Output:          none
 * Result:          a node having input as value is inserted into it's
                    appropriate location in the tree
 ----------------------------------------------------------------------------*/
void insert(char* input) {
    if(root == NULL){
        root = newNode(input); 
    }
    //TODO: your code goes here
    
}

/*-----------------------------------------------------------------------------
 * Function name:   find
 * Input:           char pointer
 * Output:          none
 * Result:          see write-up
 ----------------------------------------------------------------------------*/
void find(char* input) {

    //TODO: your code goes here
}

/*-----------------------------------------------------------------------------
 * Function name:   traverse
 * Input:           none
 * Output:          none
 * Result:          the nodes in the tree are printed in order of increasing
 *                  value
 ----------------------------------------------------------------------------*/
void traverse() {

    //TODO: your code goes here
}

//DO NOT EDIT
void insertPrompt() {
    char * input = malloc(sizeof(char)*(BUFSIZE+1));
    printf("Enter a string to insert (max length 100): ");
    scanf("%s", input);
    insert(input);
}

//DO NOT EDIT
void lookupPrompt() {
    char * input = malloc(sizeof(char)*(BUFSIZE+1));
    printf("Enter a string to look up: ");
    scanf("%s", input);
    find(input);
}

//MUST MATCH STARTER CODE ON TURNIN 
int main() {

    char input[BUFSIZE];

    printf("Binary Search Tree!\n");

    while (!feof(stdin)) {

        printf("Select an Operation: (i)nsert, (l)ookup, (p)rint in order, (q)uit: ");
        scanf("%s", input);

        if (feof(stdin)) {
            break;
        }
        if (input[0]=='i') {
            insertPrompt();
        }
        else if (input[0]=='l') {   
            lookupPrompt();
        }
        else if (input[0]=='p') {
            traverse();
        }
        else if (input[0] == 'q') {
            break;
        }
        else {
            printf("Invalid option selected, try again\n"); 
        }
    }

    return 0;
}
