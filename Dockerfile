FROM 953191494045.dkr.ecr.us-east-1.amazonaws.com/jappirepo
#Copy the build's output binary from the previous build container
#COPY --from=build /bin/HelloWorld /bin/HelloWorld
ENTRYPOINT ["/bin/HelloWorld"]
