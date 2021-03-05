# Data Migration

## Joel Fright's Data Migration Project for Sparta Global

This project was made during the 5th week of Sparta Global training. It's key functionalities include;
reading from a csv file, inserting into a database sequentially as well as concurrently via threading.

### Techniques Used

- **Connecting to a database**: This was used via JDBC, as well as through the implementation of my-sql connector
with maven.
- **Threading**: By using threading I was able to see that the insertions were much faster and as such the code was
much more efficient.
- **jUnit**: This was used to test how fast different insertions were as well as some normal unit testing.
- **Queues and HashMaps**: These were used to represent the data within the csv. HashMaps were used to
ensure the data was duplicated and Queues were used to ensure that the threads did not submit the same data twice.
- **Executor Service**: I realised whilst testing my code that the timing was not correct, this was because the code 
  was simply timing the main thread that would complete almost immediately. In order to combat this I used the executor
  framework to pool the threads together such that the program would complete timing when the last thread had finished.
- **Programming Practices**: I maintained good programming practices including SOLID and OOP throughout the project
  as well.

### Conclusions

To conclude, we can see that threading is a lot more effective in inputting data compared to sequentially. I was able
to get 5 second runtimes on a 10,000 row csv concurrently verses 2 and a half minutes sequentially, essentially 30 times faster.

#### Times recorded:
- Small CSV Sequential: 150 seconds
- Small CSV Threaded (100 Threads): 5.4 seconds
- Large CSV Threaded (10 Threads): 84 seconds
- Large CSV Threaded (50 Threads): 32 seconds
- Large CSV Threaded (100 Threads): 29 seconds

If I was to further expand upon this I would try to implement greater efficiencies within the code by using Batch 
other processes.

### Files

`src` contains all the code.

`resources` contains the csv files.